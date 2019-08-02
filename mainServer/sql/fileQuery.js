const db = require("../keys/mysql");
const Storage = require("../model/Storage");

const selectStorage = userId => {
    return new Promise((resolve, reject) => {
        let storages = new Array();
        let sql = `
        SELECT 
            userid as userId,
            storagename as storageName,
            size,
            expired,
            showOrder,
            updt_ymdt as updtYmdt,
            ispublic as isPublic
        FROM 
            storages
        WHERE
            userid = ?
        `;
        const params = [userId];

        db.query(sql, params, (err, results, fields) => {
            if (err) reject(err);
            else {
                results.forEach(result => {
                    const storage = Object.assign(new Storage(), result);
                    storages.push(storage);
                });
                resolve(storages);
            }
        });
    });
};

const insertStorage = (userId, storage, now, expired, newOrder) => {
    return new Promise((resolve, reject) => {
        let sql = `
        INSERT INTO storages (
            userid,
            storagename,
            size,
            expired,
            showOrder,
            updt_ymdt,
            ispublic
        ) VALUES (
            ?,?,?,?,?,?,?)`;

        const params = [
            userId,
            storage.storageName,
            storage.size,
            expired,
            newOrder,
            now,
            storage.isPublic
        ];

        db.query(sql, params, (err, result, fields) => {
            if (err) reject(err);
            else resolve(result.insertId);
        });
    });
};

const updateStorage = (userId, now, body) => {
    return new Promise((resolve, reject) => {
        let sql = `
            UPDATE 
                storages
            SET
                storagename = ?,
                updt_ymdt = ?
            WHERE
                userId = ?
            AND showOrder = ?
        `;

        const params = [body.storageName, now, userId, body.key];

        db.query(sql, params, (err, result, fields) => {
            if (err) reject(err);
            else resolve();
        });
    });
};

const deleteStorage = (userId, key) => {
    return new Promise((resolve, reject) => {
        let sql = `
            DELETE FROM
                storages
            WHERE
                userId = ?
            AND showOrder = ?    
        `;
        const params = [userId, key];

        db.query(sql, params, (err, result, fields) => {
            if (err) reject(err);
            else resolve();
        });
    });
};

const selectMaxKey = userId => {
    return new Promise((resolve, reject) => {
        const sql = `
            SELECT 
                IFNULL(MAX(showOrder),0) as showOrder
            FROM
                storages
        `;
        db.query(sql, (err, result, fields) => {
            if (err) reject(err);
            else resolve(result[0].showOrder);
        });
    });
};

//showOrder, page
const selectFiles = (userId, storageNum) => {
    return new Promise((resolve, reject) => {
        let sql = `
            SELECT 
                f.*,
                group_concat(l.label) as labels
            FROM 
                files as f
            LEFT JOIN
                labels as l
            ON
                f.target = l.target
            WHERE
                userId = ?
            AND storageNum = ?
            GROUP BY 
                f.target
        `;
        console.log(storageNum);
        const params = [userId, storageNum];

        db.query(sql, params, async (err, results, fields) => {
            if (err) reject(err);
            await results.forEach(result => {
                if (result.labels === null) result.labels = [];
                else result.labels = result.labels.split(",");
            });
            resolve(results);
        });
    });
};

const insertFiles = (id, files, storageNum) => {
    return new Promise(async (resolve, reject) => {
        let sql = `
        insert into files (
            userid,
            filename,
            storagenum,
            fileType,
            size,
            target,
            thumbTarget
            ) values `;
        let merge = new Array();
        console.log("insert");
        await files.forEach(file => {
            merge.push(
                `('${id}','${file.originalname}',${storageNum},'${
                    file.mimetype
                }',${file.size},'${file.location}','${file.location.replace(
                    "/first/",
                    "/copy/"
                )}')`
            );
        });
        const query = sql.concat(merge.join(","));
        db.query(query, (err, result, fields) => {
            if (err) reject(err);
            else resolve(result);
        });
    });
};

const insertLabels = files => {
    return new Promise(async (resolve, reject) => {
        let sql = `
            insert into labels (target,label) values`;
        let merge = [];
        await files.forEach(file => {
            const labels = file.labels;
            labels.forEach(label => {
                merge.push(`('${file.location}','${label}')`);
            });
        });
        const query = sql.concat(merge.join(","));
        db.query(query, (err, result) => {
            if (err) {
                console.log(err);
                reject(err);
            } else resolve(result);
        });
    });
};

const selectSearch = labels => {
    return new Promise((resolve, reject) => {
        let sql = `
    SELECT 
        f.*,
        group_concat(l.label) as labels 
    FROM
        files as f 
    LEFT JOIN 
        (
            select 
                *
            from
                labels
            where
                target IN (
                        select 
                            target 
                        from 
                            labels 
                        where 
                            labels.label IN("${labels.replace(",", "`,`")}")
                            )
                        ) as l
    ON
        f.target = l.target
    GROUP BY
        f.target
        `;
        console.log(sql);
        db.query(sql, (err, result, fields) => {
            console.log(result);

            if (err) reject(err);
            else resolve(result);
        });
    });
};

module.exports = {
    selectStorage,
    insertStorage,
    updateStorage,
    deleteStorage,
    selectMaxKey,
    selectFiles,
    insertFiles,
    insertLabels,
    selectSearch
};

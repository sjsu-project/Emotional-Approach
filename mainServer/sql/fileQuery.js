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
            else resolve();
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
                *
            FROM
                files
            WHERE
                userId = ?
            AND storageNum = ?
        `;
        console.log(storageNum);
        const params = [userId, storageNum];

        db.query(sql, params, (err, results, fields) => {
            if (err) reject(err);
            else resolve(results);
        });
    });
};

const insertFiles = (id, files, storageNum) => {
    return new Promise((resolve, reject) => {
        console.log("queryfile", files);
        let sql = `
        insert into files (
            userid,
            filename,
            storagenum,
            fileType,
            size,
            target
            ) values `;
        let merge = new Array();
        let targets = new Array();
        files.forEach(file => {
            merge.push(
                `('${id}','${file.originalname}',${storageNum},'${
                    file.mimetype
                }',${file.size},'${file.location}')`
            );
        });
        const query = sql.concat(merge.join(","));
        db.query(query, (err, result, fields) => {
            if (err) reject(err);
            else resolve({ targets, result });
        });
    });
};

const insertLabels = () => {
    return new promise((resolve, reject) => {
        resolve();
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
    insertLabels
};

const express = require("express"),
    router = express.Router(),
    upload = require("../lib/storageS3").array("files"),
    fileRepository = require("../sql/fileQuery"),
    Storage = require("../model/Storage"),
    date = require("date-and-time"),
    PythonShell = require("python-shell").PythonShell,
    path = require("path"),
    python = new PythonShell(path.join(__dirname, "./test.py"));
// // py = spawn("python", ["../../Deep_Learning/yolov3.py"]);

router.route("/ping").get((req, res) => {
    res.send("success");
});
router
    .route("/storage/:userId")
    .get((req, res) => {
        fileRepository
            .selectStorage(req.params.userId)
            .then(storages => {
                res.status(200).json({ storages });
            })
            .catch(err => {
                console.log(err);
                f;
                res.status(400).json({ err });
            });
    })
    .post(async (req, res) => {
        const userId = req.params.userId;
        const now = date.format(new Date(), "YYYY/MM/DD");
        const expired = date.format(date.addYears(new Date(), 1), "YYYY/MM/DD");

        const maxKey = await fileRepository.selectMaxKey(userId);

        fileRepository
            .insertStorage(userId, req.body, now, expired, maxKey + 1)
            .then(() => res.status(200).json({ result: "success" }))
            .catch(err => {
                console.log(err);
                res.status(400).json({ result: "failed" });
            });
    })
    .put((req, res) => {
        const now = date.format(new Date(), "YYYY/MM/DD");

        fileRepository
            .updateStorage(req.params.userId, now, req.body)
            .then(() => res.status(200).json({ result: "success" }))
            .catch(err => {
                console.log(err);
                res.status(400).json({ result: "failed" });
            });
    })
    .delete((req, res) => {
        fileRepository
            .deleteStorage(req.params.userId, req.body.key)
            .then(() => res.status(200).json({ result: "success" }))
            .catch(err => {
                console.log(err);
                res.status(400).json({ result: "failed" });
            });
    });

router
    .route("/files/:userId")
    .get((req, res) => {
        fileRepository
            .selectFiles(req.params.userId, req.query.storageNum)
            .then(results => {
                res.status(200).json({ result: results });
            })
            .catch(err => {
                console.log(err);
                res.status(500).json({ result: "failed" });
            });
    })
    .post(upload, async (req, res) => {
        const files = req.files,
            userId = req.params.userId,
            storageNum = req.body.storageNum,
            targets = [];
        await files.forEach(file => {
            targets.push(file.location);
        });
        console.log("targets", targets);

        python.send(JSON.stringify(targets));
        python.once("message", obj => {
            // console.log(typeof obj);
            // console.log("obj", obj);
            // let result = JSON.parse(obj);
            // console.log("obj", result);
            // console.log("obj", typeof result);
            // for (let key in result) {
            //     console.log(key);
            //     // if (obj[key] === "쌉불가능") continue;
            //     // else {
            //     //     files.forEach(file => {
            //     //         console.log("file", file);
            //     //         if (file.location === key) {
            //     //             file["label"] = obj[key];
            //     //         }
            //     //     });
            //     // }
            // }
            console.log("mememessage");
            console.log("obj", obj);
        });
        python.end(err => {
            if (err) err;
            console.log("err", err);
            console.log("finish");
        });
        // fileRepository.insertFiles(userId, files, storageNum);
        // fileRepository.insertLabels(files);
        res.status(200).json({ result: "success" });
    })
    .put()
    .delete();

const fu = () => {
    const result = JSON.parse(json);
    const sql = "insert into labels () values() where ";
    for (const labels in result) {
        if (labels === "쌉불가능") continue;
        else {
            db.query(sql, labels, (err, result) => {
                if (err) {
                    console.log(err);
                    return false;
                }
            });
        }
    }
};
// const spawn = require("child_process").spawn;
// const py = spawn("python", ["./test.py"]);

// py.stdout.on("data", data => {
//     console.log("data received");
//     console.log(data);
// });
// py.stdout.on("end", () => {
//     console.log("data end");
// });
// py.stdin.write("[1,2]");
// py.stdin.end();
// console.log("once");
module.exports = router;

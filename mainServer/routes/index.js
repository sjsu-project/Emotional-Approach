const express = require("express"),
    router = express.Router(),
    upload = require("../lib/storageS3").array("files"),
    fileRepository = require("../sql/fileQuery"),
    Storage = require("../model/Storage"),
    date = require("date-and-time"),
    PythonShell = require("python-shell").PythonShell,
    path = require("path");
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

router.route("/search/:search").get(async (req, res) => {
    const labels = req.params.search;
    const result = await fileRepository.selectSearch(labels);
    res.status(200).json(result);
});

router
    .route("/files/:userId")
    .get((req, res) => {
        fileRepository
            .selectFiles(req.params.userId, req.query.storageNum)
            .then(result => {
                res.status(200).json({ result });
            })
            .catch(err => {
                console.log(err);
                res.status(500).json({ result: "failed" });
            });
    })
    .post(upload, async (req, res) => {
        const files = req.files,
            userId = req.params.userId,
            storageNum = req.query.storageNum,
            targets = [];
        await files.forEach(file => {
            targets.push(file.location);
        });
        try {
            const python = new PythonShell(path.join(__dirname, "./test.py"));
            python.send(JSON.stringify(targets));
            python.once("message", async obj => {
                const filesWithLabels = await labeling(obj, files);
                fileRepository.insertFiles(userId, filesWithLabels, storageNum);
                fileRepository.insertLabels(filesWithLabels);
                res.status(200).json({ result: "success" });
            });

            python.end(err => {
                if (err) {
                    console.log("err", err);
                    res.status(400).json({ result: "failed" });
                }
                console.log("finish");
            });
        } catch (err) {
            console.log(err);
        }
    })
    .put()
    .delete();
const labeling = (obj, files) => {
    return new Promise(async (resolve, reject) => {
        let result = JSON.parse(obj.replace(/'/g, '"'));
        await Object.keys(result).forEach(key => {
            files.some(file => {
                //if (result[key] === "쌉불가능")
                if (file.location === key) {
                    file["labels"] = result[key];
                    return true;
                }
            });
        });
        resolve(files);
    });
};
module.exports = router;

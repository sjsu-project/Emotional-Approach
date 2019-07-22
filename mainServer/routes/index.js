var express = require("express");
var router = express.Router();
const s3 = require("../lib/storageS3");
const fileRepository = require("../sql/fileQuery");
const Storage = require("../model/Storage");
const date = require("date-and-time");

router.get("/a", (req, res) => {
    const current = date.format(new Date(), "YYYY/MM/DD");

    console.log(typeof current);
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
});

module.exports = router;

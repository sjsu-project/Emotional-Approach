const multer = require("multer");
const multerS3 = require("multer-s3");
const path = require("path");
var AWS = require("aws-sdk");
AWS.config.loadFromPath("keys/config.json");
const s3 = new AWS.S3();

module.exports = multer({
    storage: multerS3({
        s3: s3,
        bucket: "traveloguesjsu", //버켓 이름 넣어줌
        key: function(req, file, cb) {
            // 버켓에 담을 이름 설정
            const filename =
                "first/" +
                Date.now().toString() +
                "_" +
                req.params.userId +
                "_" +
                file.originalname;
            cb(null, filename);
        },
        acl: "public-read-write" //권한
    })
});
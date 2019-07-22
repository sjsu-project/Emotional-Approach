var express = require("express");
var router = express.Router();

/* GET users listing. */
router.get("/", function(req, res, next) {
    const result = "";
    res.status(200).json({
        result
    });
});

module.exports = router;

module.exports = class Storage {
    constructor(
        userId,
        storageName,
        size,
        expired,
        showOrder,
        updtYmdt,
        isPublic
    ) {
        this.userId = userId;
        this.storageName = storageName;
        this.size = size;
        this.expired = expired;
        this.showOrder = showOrder;
        this.updtYmdt = updtYmdt;
        this.isPublic = isPublic;
        console.log("123", this);
    }
};

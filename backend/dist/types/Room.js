"use strict";
exports.__esModule = true;
exports.Room = void 0;
var schema_1 = require("@nexus/schema");
exports.Room = schema_1.objectType({
    name: "Room",
    definition: function (t) {
        t.model.id();
        t.model.createdAt();
        t.model.updatedAt();
        t.model.title();
        t.model.thumbnailUrl();
        t.model.roomUrl();
        t.model.roomCredentials();
        t.model.clicks();
        t.model.deletedAt();
    }
});
//# sourceMappingURL=Room.js.map
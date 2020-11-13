"use strict";
exports.__esModule = true;
exports.RoomClick = void 0;
var schema_1 = require("@nexus/schema");
exports.RoomClick = schema_1.objectType({
    name: "RoomClick",
    definition: function (t) {
        t.model.id();
        t.model.createdAt();
    }
});
//# sourceMappingURL=RoomClick.js.map
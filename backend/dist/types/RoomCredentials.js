"use strict";
exports.__esModule = true;
exports.RoomCredentials = void 0;
var schema_1 = require("@nexus/schema");
exports.RoomCredentials = schema_1.objectType({
    name: "RoomCredentials",
    definition: function (t) {
        t.model.password();
        t.model.roomId();
    }
});
//# sourceMappingURL=RoomCredentials.js.map
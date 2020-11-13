"use strict";
exports.__esModule = true;
exports.Category = void 0;
var schema_1 = require("@nexus/schema");
exports.Category = schema_1.objectType({
    name: "Category",
    definition: function (t) {
        t.model.id();
        t.model.updatedAt();
        t.model.name();
        t.model.thumbnailUrl();
        t.model.rooms();
    }
});
//# sourceMappingURL=Category.js.map
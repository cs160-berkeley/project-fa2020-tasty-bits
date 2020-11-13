"use strict";
exports.__esModule = true;
exports.Query = void 0;
var schema_1 = require("@nexus/schema");
exports.Query = schema_1.queryType({
    definition: function (t) {
        t.list.field("categoryRooms", {
            type: "Room",
            nullable: false,
            args: {
                categoryId: schema_1.idArg({ required: true })
            },
            resolve: function (_, _a, ctx) {
                var categoryId = _a.categoryId;
                return ctx.prisma.room.findMany({
                    orderBy: {
                        id: "desc"
                    },
                    where: {
                        categoryId: {
                            equals: categoryId
                        },
                        createdAt: {
                            gt: new Date(+new Date() - 60 * 60 * 1000 * 24 * 7)
                        },
                        deletedAt: {
                            equals: null
                        }
                    },
                    include: {
                        clicks: {
                            orderBy: {
                                id: "desc"
                            }
                        }
                    }
                });
            }
        });
        t.list.field("allCategories", {
            type: "Category",
            nullable: false,
            resolve: function (_, args, ctx) {
                return ctx.prisma.category.findMany({
                    orderBy: {
                        id: "asc"
                    }
                });
            }
        });
    }
});
//# sourceMappingURL=Query.js.map
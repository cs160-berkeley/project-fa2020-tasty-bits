import { idArg, queryType, stringArg } from "@nexus/schema";

export const Query = queryType({
  definition(t) {
    t.list.field("categoryRooms", {
      type: "Room",
      nullable: false,
      args: {
        categoryId: idArg({ required: true }),
      },
      resolve: (_, { categoryId }, ctx) => {
        return ctx.prisma.room.findMany({
          orderBy: {
            id: "desc",
          },
          where: {
            categoryId: {
              equals: categoryId,
            },
            createdAt: {
              gt: new Date(+new Date() - 60 * 60 * 1000 * 24 * 7),
            },
            deletedAt: {
              equals: null,
            },
          },
          include: {
            clicks: {
              orderBy: {
                id: "desc",
              },
            },
          },
        });
      },
    });

    t.list.field("allCategories", {
      type: "Category",
      nullable: false,
      resolve: (_, args, ctx) => {
        return ctx.prisma.category.findMany({
          orderBy: {
            id: "asc",
          },
        });
      },
    });
  },
});

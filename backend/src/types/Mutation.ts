import { idArg, intArg, mutationType, stringArg } from "@nexus/schema";
import { FindManyRoomArgs } from "nexus-plugin-prisma/client";
import {
  validateRoomCredentials,
  validateRoomLink,
  validateRoomTitle,
} from "../services/validator";
import { RoomCredentials } from "./RoomCredentials";

export const Mutation = mutationType({
  definition(t) {
    t.field("createRoom", {
      type: "Room",
      nullable: true,
      args: {
        categoryId: idArg({ required: true }),
        title: stringArg({ required: true }),
        roomLink: stringArg({ required: true }),
      },
      resolve: (_, { categoryId, title, roomLink }, ctx) => {
        if (!validateRoomLink(roomLink)) {
          throw new Error("ERROR_INVALID_ROOM_LINK");
        }

        if (!validateRoomTitle(title)) {
          throw new Error("ERROR_INVALID_ROOM_TITLE");
        }

        return ctx.prisma.room.create({
          data: {
            category: {
              connect: {
                id: categoryId,
              },
            },
            title: title,
            roomCredentials: {
              create: {},
            },
            roomUrl: roomLink,
          },
          include: {
            roomCredentials: true,
            clicks: true,
          },
        });
      },
    });

    t.field("editRoomTitle", {
      type: "Room",
      nullable: true,
      args: {
        roomId: idArg({ required: true }),
        roomPassword: stringArg({ required: true }),
        title: stringArg({ required: true }),
      },
      resolve: async (_, { roomId, roomPassword, title }, ctx) => {
        if (
          !(await validateRoomCredentials({
            ctx,
            roomId,
            password: roomPassword,
          }))
        ) {
          throw new Error("ERROR_INVALID_ROOM_PASSWORD");
        }

        if (title && !validateRoomTitle(title)) {
          throw new Error("ERROR_INVALID_ROOM_TITLE");
        }

        return ctx.prisma.room.update({
          where: {
            id: roomId,
          },
          data: {
            title: title,
          },
          include: {
            clicks: true,
          },
        });
      },
    });

    t.field("deleteRoom", {
      type: "Room",
      nullable: true,
      args: {
        roomId: idArg({ required: true }),
        roomPassword: stringArg({ required: true }),
      },
      resolve: async (_, { roomId, roomPassword }, ctx) => {
        if (
          !(await validateRoomCredentials({
            ctx,
            roomId,
            password: roomPassword,
          }))
        ) {
          throw new Error("ERROR_INVALID_ROOM_PASSWORD");
        }

        return ctx.prisma.room.update({
          where: {
            id: roomId,
          },
          data: {
            deletedAt: new Date(),
          },
          include: {
            clicks: true,
          },
        });
      },
    });

    t.field("roomClicked", {
      type: "RoomClick",
      nullable: true,
      args: {
        roomId: idArg({ required: true }),
      },
      resolve: async (_, { roomId }, ctx) => {
        return ctx.prisma.roomClick.create({
          data: {
            room: {
              connect: {
                id: roomId,
              },
            },
          },
        });
      },
    });
  },
});

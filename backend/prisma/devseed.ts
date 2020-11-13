require("dotenv").config();

import {
  Category,
  PrismaClient,
  Room,
  RoomClick,
} from "nexus-plugin-prisma/client";
const prisma = new PrismaClient();

const CATEGORIES = [
  "Study Libraries",
  "Party Games or Video Games",
  "Meet and Hangout",
  "Cooking or Excercise",
  "Venting Talks",
  "Custom Activities",
];

const TITLE_CHAR_MAX = 35;
const ROOMS_MAX = 15;
const CLICKS_MAX = 45;

function randomDate(start: Date, end: Date) {
  return new Date(
    start.getTime() + Math.random() * (end.getTime() - start.getTime())
  );
}

function makeid(length: number) {
  let result = "";
  let characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789              ";
  let charactersLength = characters.length;
  for (var i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
}
const createClick = (): Partial<RoomClick> => {
  return {
    createdAt: randomDate(
      new Date(+new Date() - 60 * 60 * 1000 * 24),
      new Date()
    ),
  };
};

const genObjects = (objMax: number, gen: () => any) => {
  let objNum = Math.random() * objMax;
  const objs = [];
  for (let i = 0; i < objNum; i++) {
    objs.push(gen());
  }
  return objs;
};

const createRoom = (): Partial<Room> & { clicks: { create: RoomClick[] } } => {
  return {
    title: "MyTitle_" + makeid(Math.random() * TITLE_CHAR_MAX),
    roomUrl:
      "https://berkeley.zoom.us/j/93079690124?pwd=cERxaEdJQVpCV0dHajNBZlJzeGN4QT03",
    clicks: {
      create: genObjects(CLICKS_MAX, createClick),
    },
  };
};

async function main() {
  let created: any[] = [];
  for (let c of CATEGORIES) {
    created.push(
      prisma.category.upsert({
        where: {
          name: c,
        },
        create: {
          name: c,
          rooms: {
            create: genObjects(ROOMS_MAX, createRoom),
          },
        },
        update: {},
      })
    );
  }
  await Promise.all(created).then((v) => {
    console.log(v);
  });
}

main().finally(async () => {
  await prisma.$disconnect();
});

require("dotenv").config();

import { PrismaClient } from "nexus-plugin-prisma/client";
const prisma = new PrismaClient();

const CATEGORIES = [
  "Study Libraries",
  "Party Games or Video Games",
  "Meet and Hangout",
  "Cooking or Excercise",
  "Venting Talks",
  "Custom Activities",
];

async function main() {
  let created = [];
  for (let c of CATEGORIES) {
    created.push(
      await prisma.category.upsert({
        where: {
          name: c,
        },
        create: {
          name: c,
        },
        update: {},
      })
    );
  }

  console.log(created);
}

main().finally(async () => {
  await prisma.$disconnect();
});

require('dotenv').config();

import { PrismaClient } from 'nexus-plugin-prisma/client';
const prisma = new PrismaClient();

const CATEGORIES = [
  'classPlanning',
  'enrollment',
  'clubsAndDecals',
  'financialAid',
  'housing',
  'jobHunting',
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

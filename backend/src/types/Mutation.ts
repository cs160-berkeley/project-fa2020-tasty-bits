import {
  arg,
  booleanArg,
  idArg,
  intArg,
  mutationType,
  stringArg,
} from '@nexus/schema';
import { getUserId } from '../utils';

export const Mutation = mutationType({
  definition(t) {
    t.field('upsertUser', {
      type: 'User',
      nullable: true,
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.user.upsert({
          where: {
            id: userId,
          },
          create: {
            id: userId,
          },
          update: {},
        });
      },
    });

    t.field('editUser', {
      type: 'User',
      nullable: true,
      args: {
        name: stringArg({ required: false }),
        biography: stringArg({ required: false }),
        class: stringArg({ required: false }),
        major: stringArg({ required: false }),
        profileVisibility: arg({ required: false, type: 'ProfileVisibility' }),
        linkedin: stringArg({ required: false }),
        facebook: stringArg({ required: false }),
        twitter: stringArg({ required: false }),
        instagram: stringArg({ required: false }),
        snapchat: stringArg({ required: false }),
        tiktok: stringArg({ required: false }),
        studentType: arg({ required: false, type: 'StudentType' }),
        openToHelp: booleanArg({ required: false }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.user.update({
          where: {
            id: userId,
          },
          data: {
            name: args.name || undefined,
            biography: args.biography || undefined,
            class: args.class || undefined,
            major: args.major || undefined,
            profileVisibility: args.profileVisibility || undefined,
            linkedin: args.linkedin || undefined,
            facebook: args.facebook || undefined,
            twitter: args.twitter || undefined,
            instagram: args.instagram || undefined,
            snapchat: args.snapchat || undefined,
            tiktok: args.tiktok || undefined,
            studentType: args.studentType || undefined,
            openToHelp: args.openToHelp || undefined,
          },
        });
      },
    });

    t.field('createQuestion', {
      type: 'Question',
      nullable: true,
      args: {
        categoryIds: idArg({ list: true, required: true }),
        title: stringArg({ required: true }),
        description: stringArg({ required: false }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.question.create({
          data: {
            categories: {
              connect: args.categoryIds.map((categoryId: string) => {
                return { id: categoryId };
              }),
            },
            title: args.title,
            description: args.description,
            user: {
              connect: {
                id: userId,
              },
            },
          },
          include: {
            votes: true,
            clicks: true,
            categories: true,
          },
        });
      },
    });

    t.field('editQuestion', {
      type: 'Question',
      nullable: true,
      args: {
        id: idArg({ required: true }),
        title: stringArg({ required: false }),
        description: stringArg({ required: false }),
        delete: booleanArg({ required: false }),
      },
      resolve: (_, args, ctx) => {
        return ctx.prisma.question.update({
          where: {
            id: args.id,
          },
          data: {
            title: args.title || undefined,
            description: args.description || undefined,
            deletedAt: (args.delete && new Date()) || undefined,
          },
          include: {
            votes: true,
            clicks: true,
            categories: true,
          },
        });
      },
    });

    t.field('createAnswer', {
      type: 'Answer',
      nullable: true,
      args: {
        questionId: idArg({ required: true }),
        content: stringArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.answer.create({
          data: {
            content: args.content,
            question: {
              connect: {
                id: args.questionId,
              },
            },
            user: {
              connect: {
                id: userId,
              },
            },
          },
          include: {
            votes: true,
          },
        });
      },
    });

    t.field('editAnswer', {
      type: 'Answer',
      nullable: true,
      args: {
        id: idArg({ required: true }),
        content: stringArg({ required: false }),
        delete: booleanArg({ required: false }),
      },
      resolve: (_, args, ctx) => {
        return ctx.prisma.answer.update({
          where: {
            id: args.id,
          },
          data: {
            content: args.content || undefined,
            deletedAt: (args.delete && new Date()) || undefined,
          },
          include: {
            votes: true,
          },
        });
      },
    });

    t.field('upsertQuestionVote', {
      type: 'QuestionVote',
      nullable: true,
      args: {
        questionId: idArg({ required: true }),
        upDown: booleanArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.questionVote.upsert({
          where: {
            questionId_userId: {
              questionId: args.questionId,
              userId: userId,
            },
          },
          create: {
            upDown: args.upDown,
            user: {
              connect: {
                id: userId,
              },
            },
            question: {
              connect: {
                id: args.questionId,
              },
            },
          },
          update: {
            upDown: args.upDown,
          },
        });
      },
    });

    t.field('upsertQuestionClick', {
      type: 'QuestionClick',
      nullable: true,
      args: {
        questionId: idArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.questionClick.upsert({
          where: {
            questionId_userId: {
              questionId: args.questionId,
              userId: userId,
            },
          },
          create: {
            user: {
              connect: {
                id: userId,
              },
            },
            question: {
              connect: {
                id: args.questionId,
              },
            },
          },
          update: {},
        });
      },
    });

    t.field('upsertAnswerVote', {
      type: 'AnswerVote',
      nullable: true,
      args: {
        answerId: idArg({ required: true }),
        upDown: booleanArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.answerVote.upsert({
          where: {
            answerId_userId: {
              answerId: args.answerId,
              userId: userId,
            },
          },
          create: {
            upDown: args.upDown,
            user: {
              connect: {
                id: userId,
              },
            },
            answer: {
              connect: {
                id: args.answerId,
              },
            },
          },
          update: {
            upDown: args.upDown,
          },
        });
      },
    });

    t.field('createChatroom', {
      type: 'Chatroom',
      nullable: true,
      args: {
        class: stringArg({ required: false }),
        major: stringArg({ required: false }),
        studentType: arg({ required: false, type: 'StudentType' }),
      },
      resolve: async (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        const currentlyInChatrooms = await ctx.prisma.user
          .findOne({
            where: {
              id: userId,
            },
          })
          .chatrooms({
            include: {
              users: true,
            },
          });

        const currentlyChattingWith: string[] = [];
        currentlyInChatrooms.forEach((c) => {
          currentlyChattingWith.push(
            ...c.users.map((u) => u.id).filter((id) => id != userId)
          );
        });

        const chatSelectionUserPool = await ctx.prisma.user.findMany({
          where: {
            class: args.class || undefined,
            major: args.major || undefined,
            studentType: args.studentType || undefined,
            openToHelp: true,
            id: {
              notIn: currentlyChattingWith,
            },
          },
        });

        const random = Math.floor(Math.random() * chatSelectionUserPool.length);
        const chosenUser = chatSelectionUserPool[random];

        return ctx.prisma.chatroom.create({
          data: {
            users: {
              connect: [
                {
                  id: userId,
                },
                {
                  id: chosenUser.id,
                },
              ],
            },
          },
          include: {
            users: true,
          },
        });
      },
    });

    t.field('createMessage', {
      type: 'Message',
      nullable: true,
      args: {
        chatroomId: idArg({ required: true }),
        content: stringArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.message.create({
          data: {
            content: args.content,
            chatroom: {
              connect: {
                id: args.chatroomId,
              },
            },
            user: {
              connect: {
                id: userId,
              },
            },
          },
        });
      },
    });
  },
});

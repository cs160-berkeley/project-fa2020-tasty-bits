import { enumType, objectType } from '@nexus/schema';

export const User = objectType({
  name: 'User',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();
    t.model.name();
    t.model.biography();
    t.model.class();
    t.model.major();
    t.model.profileVisibility();
    t.model.linkedin();
    t.model.facebook();
    t.model.twitter();
    t.model.instagram();
    t.model.snapchat();
    t.model.tiktok();
    t.model.studentType();
    t.model.openToHelp();
    t.model.chatrooms();
    t.model.questions();
    t.model.questionVotes();
    t.model.questionClicks();
    t.model.answerVotes();
    t.model.answers();
    t.model.messages();

    t.int('questionVoteScore', {
      resolve: async (parent, args, ctx) => {
        const votes = await ctx.prisma.user
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .questionVotes();
        return votes.reduce((prev, curr) => prev + +curr.upDown, 0);
      },
    });

    t.int('answerVoteScore', {
      resolve: async (parent, args, ctx) => {
        const votes = await ctx.prisma.user
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .answerVotes();
        return votes.reduce((prev, curr) => prev + +curr.upDown, 0);
      },
    });

    t.int('questionScore', {
      resolve: async (parent, args, ctx) => {
        const questions = await ctx.prisma.user
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .questions({
            include: {
              votes: true,
            },
          });
        return questions.reduce(
          (prev, curr) =>
            prev + curr.votes.reduce((prev, curr) => prev + +curr.upDown, 1),
          0
        );
      },
    });

    t.int('answerScore', {
      resolve: async (parent, args, ctx) => {
        const answers = await ctx.prisma.user
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .answers({
            include: {
              votes: true,
            },
          });
        return answers.reduce(
          (prev, curr) =>
            prev + curr.votes.reduce((prev, curr) => prev + +curr.upDown, 1),
          0
        );
      },
    });
  },
});

export const Category = objectType({
  name: 'Category',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();
    t.model.name();
    t.model.questions();
  },
});

export const Question = objectType({
  name: 'Question',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();
    t.model.title();
    t.model.description();
    t.model.votes();
    t.model.clicks();
    t.model.answers();
    t.model.deletedAt();

    t.int('voteScore', {
      resolve: async (parent, args, ctx) => {
        const votes = await ctx.prisma.question
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .votes();
        return votes.reduce((prev, curr) => prev + +curr.upDown, 0);
      },
    });

    t.int('clickScore', {
      resolve: async (parent, args, ctx) => {
        const clicks = await ctx.prisma.question
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .clicks();
        return clicks.length;
      },
    });

    t.model.user();
    t.model.userId();

    t.model.categories();
  },
});

export const QuestionVote = objectType({
  name: 'QuestionVote',
  definition(t) {
    t.model.createdAt();
    t.model.updatedAt();
    t.model.upDown();

    t.model.question();
    t.model.questionId();

    t.model.user();
    t.model.userId();
  },
});

export const QuestionClick = objectType({
  name: 'QuestionClick',
  definition(t) {
    t.model.createdAt();
    t.model.updatedAt();

    t.model.question();
    t.model.questionId();

    t.model.user();
    t.model.userId();
  },
});

export const Answer = objectType({
  name: 'Answer',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();

    t.model.content();
    t.model.votes();

    t.model.question();
    t.model.questionId();

    t.model.deletedAt();

    t.int('voteScore', {
      resolve: async (parent, args, ctx) => {
        const votes = await ctx.prisma.answer
          .findOne({
            where: {
              id: parent.id,
            },
          })
          .votes();
        return votes.reduce((prev, curr) => prev + +curr.upDown, 0);
      },
    });
    t.model.user();
    t.model.userId();
  },
});

export const AnswerVote = objectType({
  name: 'AnswerVote',
  definition(t) {
    t.model.createdAt();
    t.model.updatedAt();
    t.model.upDown();

    t.model.answer();
    t.model.answerId();

    t.model.user();
    t.model.userId();
  },
});

export const Chatroom = objectType({
  name: 'Chatroom',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();

    t.model.messages();

    t.model.users();
  },
});

export const Message = objectType({
  name: 'Message',
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();
    t.model.content();
    t.model.user();
    t.model.userId();
    t.model.chatroom();
    t.model.chatroomId();
  },
});

export const SearchResults = objectType({
  name: 'SearchResults',
  definition(t) {
    t.list.field('answers', { type: 'Answer' });
    t.list.field('questions', { type: 'Question' });
    t.list.field('users', { type: 'User' });
  },
});

export const SentimentEnum = enumType({
  name: 'SentimentEnum',
  members: [
    'VERY_HAPPY',
    'HAPPY',
    'SATISFIED',
    'NO_OPINION',
    'FRUSTRATED',
    'ANGRY',
    'VERY_ANGRY',
  ],
});

export const SentimentResult = objectType({
  name: 'SentimentResult',
  definition(t) {
    t.field('sentiment', { type: 'SentimentEnum' });
    t.float('score');
    t.float('magnitude');
  },
});

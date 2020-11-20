import * as Typegen from 'nexus-plugin-prisma/typegen'
import * as Prisma from '@prisma/client';

// Pagination type
type Pagination = {
  first?: boolean
  last?: boolean
  before?: boolean
  after?: boolean
}

// Prisma custom scalar names
type CustomScalars = 'DateTime'

// Prisma model type definitions
interface PrismaModels {
  User: Prisma.User
  Category: Prisma.Category
  Question: Prisma.Question
  QuestionVote: Prisma.QuestionVote
  QuestionClick: Prisma.QuestionClick
  Answer: Prisma.Answer
  AnswerVote: Prisma.AnswerVote
  Chatroom: Prisma.Chatroom
  Message: Prisma.Message
}

// Prisma input types metadata
interface NexusPrismaInputs {
  Query: {
    users: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'name' | 'biography' | 'class' | 'major' | 'profileVisibility' | 'linkedin' | 'facebook' | 'twitter' | 'instagram' | 'snapchat' | 'tiktok' | 'openToHelp' | 'studentType' | 'chatrooms' | 'questions' | 'questionVotes' | 'questionClicks' | 'answerVotes' | 'answers' | 'messages'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'name' | 'biography' | 'class' | 'major' | 'profileVisibility' | 'linkedin' | 'facebook' | 'twitter' | 'instagram' | 'snapchat' | 'tiktok' | 'openToHelp' | 'studentType'
    }
    categories: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'name' | 'questions'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'name'
    }
    questions: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'votes' | 'clicks' | 'answers' | 'deletedAt' | 'user' | 'userId' | 'categories'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'deletedAt' | 'userId'
    }
    questionVotes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'questionId' | 'userId'
    }
    questionClicks: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'questionId' | 'userId'
    }
    answers: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'votes' | 'question' | 'questionId' | 'deletedAt' | 'user' | 'userId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'questionId' | 'deletedAt' | 'userId'
    }
    answerVotes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'answer' | 'answerId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'answerId' | 'userId'
    }
    chatrooms: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'messages' | 'users'
      ordering: 'id' | 'createdAt' | 'updatedAt'
    }
    messages: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'user' | 'userId' | 'chatroom' | 'chatroomId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'userId' | 'chatroomId'
    }
  },
  User: {
    chatrooms: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'messages' | 'users'
      ordering: 'id' | 'createdAt' | 'updatedAt'
    }
    questions: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'votes' | 'clicks' | 'answers' | 'deletedAt' | 'user' | 'userId' | 'categories'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'deletedAt' | 'userId'
    }
    questionVotes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'questionId' | 'userId'
    }
    questionClicks: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'questionId' | 'userId'
    }
    answerVotes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'answer' | 'answerId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'answerId' | 'userId'
    }
    answers: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'votes' | 'question' | 'questionId' | 'deletedAt' | 'user' | 'userId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'questionId' | 'deletedAt' | 'userId'
    }
    messages: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'user' | 'userId' | 'chatroom' | 'chatroomId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'userId' | 'chatroomId'
    }
  }
  Category: {
    questions: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'votes' | 'clicks' | 'answers' | 'deletedAt' | 'user' | 'userId' | 'categories'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'title' | 'description' | 'deletedAt' | 'userId'
    }
  }
  Question: {
    votes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'questionId' | 'userId'
    }
    clicks: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'question' | 'questionId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'questionId' | 'userId'
    }
    answers: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'votes' | 'question' | 'questionId' | 'deletedAt' | 'user' | 'userId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'questionId' | 'deletedAt' | 'userId'
    }
    categories: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'name' | 'questions'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'name'
    }
  }
  QuestionVote: {

  }
  QuestionClick: {

  }
  Answer: {
    votes: {
      filtering: 'AND' | 'OR' | 'NOT' | 'createdAt' | 'updatedAt' | 'upDown' | 'answer' | 'answerId' | 'user' | 'userId'
      ordering: 'createdAt' | 'updatedAt' | 'upDown' | 'answerId' | 'userId'
    }
  }
  AnswerVote: {

  }
  Chatroom: {
    messages: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'content' | 'user' | 'userId' | 'chatroom' | 'chatroomId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'content' | 'userId' | 'chatroomId'
    }
    users: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'name' | 'biography' | 'class' | 'major' | 'profileVisibility' | 'linkedin' | 'facebook' | 'twitter' | 'instagram' | 'snapchat' | 'tiktok' | 'openToHelp' | 'studentType' | 'chatrooms' | 'questions' | 'questionVotes' | 'questionClicks' | 'answerVotes' | 'answers' | 'messages'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'name' | 'biography' | 'class' | 'major' | 'profileVisibility' | 'linkedin' | 'facebook' | 'twitter' | 'instagram' | 'snapchat' | 'tiktok' | 'openToHelp' | 'studentType'
    }
  }
  Message: {

  }
}

// Prisma output types metadata
interface NexusPrismaOutputs {
  Query: {
    user: 'User'
    users: 'User'
    category: 'Category'
    categories: 'Category'
    question: 'Question'
    questions: 'Question'
    questionVote: 'QuestionVote'
    questionVotes: 'QuestionVote'
    questionClick: 'QuestionClick'
    questionClicks: 'QuestionClick'
    answer: 'Answer'
    answers: 'Answer'
    answerVote: 'AnswerVote'
    answerVotes: 'AnswerVote'
    chatroom: 'Chatroom'
    chatrooms: 'Chatroom'
    message: 'Message'
    messages: 'Message'
  },
  Mutation: {
    createOneUser: 'User'
    updateOneUser: 'User'
    updateManyUser: 'BatchPayload'
    deleteOneUser: 'User'
    deleteManyUser: 'BatchPayload'
    upsertOneUser: 'User'
    createOneCategory: 'Category'
    updateOneCategory: 'Category'
    updateManyCategory: 'BatchPayload'
    deleteOneCategory: 'Category'
    deleteManyCategory: 'BatchPayload'
    upsertOneCategory: 'Category'
    createOneQuestion: 'Question'
    updateOneQuestion: 'Question'
    updateManyQuestion: 'BatchPayload'
    deleteOneQuestion: 'Question'
    deleteManyQuestion: 'BatchPayload'
    upsertOneQuestion: 'Question'
    createOneQuestionVote: 'QuestionVote'
    updateOneQuestionVote: 'QuestionVote'
    updateManyQuestionVote: 'BatchPayload'
    deleteOneQuestionVote: 'QuestionVote'
    deleteManyQuestionVote: 'BatchPayload'
    upsertOneQuestionVote: 'QuestionVote'
    createOneQuestionClick: 'QuestionClick'
    updateOneQuestionClick: 'QuestionClick'
    updateManyQuestionClick: 'BatchPayload'
    deleteOneQuestionClick: 'QuestionClick'
    deleteManyQuestionClick: 'BatchPayload'
    upsertOneQuestionClick: 'QuestionClick'
    createOneAnswer: 'Answer'
    updateOneAnswer: 'Answer'
    updateManyAnswer: 'BatchPayload'
    deleteOneAnswer: 'Answer'
    deleteManyAnswer: 'BatchPayload'
    upsertOneAnswer: 'Answer'
    createOneAnswerVote: 'AnswerVote'
    updateOneAnswerVote: 'AnswerVote'
    updateManyAnswerVote: 'BatchPayload'
    deleteOneAnswerVote: 'AnswerVote'
    deleteManyAnswerVote: 'BatchPayload'
    upsertOneAnswerVote: 'AnswerVote'
    createOneChatroom: 'Chatroom'
    updateOneChatroom: 'Chatroom'
    updateManyChatroom: 'BatchPayload'
    deleteOneChatroom: 'Chatroom'
    deleteManyChatroom: 'BatchPayload'
    upsertOneChatroom: 'Chatroom'
    createOneMessage: 'Message'
    updateOneMessage: 'Message'
    updateManyMessage: 'BatchPayload'
    deleteOneMessage: 'Message'
    deleteManyMessage: 'BatchPayload'
    upsertOneMessage: 'Message'
  },
  User: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    name: 'String'
    biography: 'String'
    class: 'String'
    major: 'String'
    profileVisibility: 'ProfileVisibility'
    linkedin: 'String'
    facebook: 'String'
    twitter: 'String'
    instagram: 'String'
    snapchat: 'String'
    tiktok: 'String'
    openToHelp: 'Boolean'
    studentType: 'StudentType'
    chatrooms: 'Chatroom'
    questions: 'Question'
    questionVotes: 'QuestionVote'
    questionClicks: 'QuestionClick'
    answerVotes: 'AnswerVote'
    answers: 'Answer'
    messages: 'Message'
  }
  Category: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    name: 'String'
    questions: 'Question'
  }
  Question: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    title: 'String'
    description: 'String'
    votes: 'QuestionVote'
    clicks: 'QuestionClick'
    answers: 'Answer'
    deletedAt: 'DateTime'
    user: 'User'
    userId: 'String'
    categories: 'Category'
  }
  QuestionVote: {
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    upDown: 'Boolean'
    question: 'Question'
    questionId: 'String'
    user: 'User'
    userId: 'String'
  }
  QuestionClick: {
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    question: 'Question'
    questionId: 'String'
    user: 'User'
    userId: 'String'
  }
  Answer: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    content: 'String'
    votes: 'AnswerVote'
    question: 'Question'
    questionId: 'String'
    deletedAt: 'DateTime'
    user: 'User'
    userId: 'String'
  }
  AnswerVote: {
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    upDown: 'Boolean'
    answer: 'Answer'
    answerId: 'String'
    user: 'User'
    userId: 'String'
  }
  Chatroom: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    messages: 'Message'
    users: 'User'
  }
  Message: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    content: 'String'
    user: 'User'
    userId: 'String'
    chatroom: 'Chatroom'
    chatroomId: 'String'
  }
}

// Helper to gather all methods relative to a model
interface NexusPrismaMethods {
  User: Typegen.NexusPrismaFields<'User'>
  Category: Typegen.NexusPrismaFields<'Category'>
  Question: Typegen.NexusPrismaFields<'Question'>
  QuestionVote: Typegen.NexusPrismaFields<'QuestionVote'>
  QuestionClick: Typegen.NexusPrismaFields<'QuestionClick'>
  Answer: Typegen.NexusPrismaFields<'Answer'>
  AnswerVote: Typegen.NexusPrismaFields<'AnswerVote'>
  Chatroom: Typegen.NexusPrismaFields<'Chatroom'>
  Message: Typegen.NexusPrismaFields<'Message'>
  Query: Typegen.NexusPrismaFields<'Query'>
  Mutation: Typegen.NexusPrismaFields<'Mutation'>
}

interface NexusPrismaGenTypes {
  inputs: NexusPrismaInputs
  outputs: NexusPrismaOutputs
  methods: NexusPrismaMethods
  models: PrismaModels
  pagination: Pagination
  scalars: CustomScalars
}

declare global {
  interface NexusPrismaGen extends NexusPrismaGenTypes {}

  type NexusPrisma<
    TypeName extends string,
    ModelOrCrud extends 'model' | 'crud'
  > = Typegen.GetNexusPrisma<TypeName, ModelOrCrud>;
}
  
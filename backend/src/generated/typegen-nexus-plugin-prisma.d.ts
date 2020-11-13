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
  Category: Prisma.Category
  Room: Prisma.Room
  RoomClick: Prisma.RoomClick
  RoomCredentials: Prisma.RoomCredentials
}

// Prisma input types metadata
interface NexusPrismaInputs {
  Query: {
    categories: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'name' | 'thumbnailUrl' | 'rooms'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'name' | 'thumbnailUrl'
    }
    rooms: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'title' | 'roomUrl' | 'thumbnailUrl' | 'roomCredentials' | 'category' | 'categoryId' | 'clicks' | 'deletedAt'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'title' | 'roomUrl' | 'thumbnailUrl' | 'categoryId' | 'deletedAt'
    }
    roomClicks: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'room' | 'roomId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'roomId'
    }
    roomCredentials: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'room' | 'roomId' | 'password'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'roomId' | 'password'
    }
  },
  Category: {
    rooms: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'title' | 'roomUrl' | 'thumbnailUrl' | 'roomCredentials' | 'category' | 'categoryId' | 'clicks' | 'deletedAt'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'title' | 'roomUrl' | 'thumbnailUrl' | 'categoryId' | 'deletedAt'
    }
  }
  Room: {
    clicks: {
      filtering: 'AND' | 'OR' | 'NOT' | 'id' | 'createdAt' | 'updatedAt' | 'room' | 'roomId'
      ordering: 'id' | 'createdAt' | 'updatedAt' | 'roomId'
    }
  }
  RoomClick: {

  }
  RoomCredentials: {

  }
}

// Prisma output types metadata
interface NexusPrismaOutputs {
  Query: {
    category: 'Category'
    categories: 'Category'
    room: 'Room'
    rooms: 'Room'
    roomClick: 'RoomClick'
    roomClicks: 'RoomClick'
    roomCredentials: 'RoomCredentials'
    roomCredentials: 'RoomCredentials'
  },
  Mutation: {
    createOneCategory: 'Category'
    updateOneCategory: 'Category'
    updateManyCategory: 'BatchPayload'
    deleteOneCategory: 'Category'
    deleteManyCategory: 'BatchPayload'
    upsertOneCategory: 'Category'
    createOneRoom: 'Room'
    updateOneRoom: 'Room'
    updateManyRoom: 'BatchPayload'
    deleteOneRoom: 'Room'
    deleteManyRoom: 'BatchPayload'
    upsertOneRoom: 'Room'
    createOneRoomClick: 'RoomClick'
    updateOneRoomClick: 'RoomClick'
    updateManyRoomClick: 'BatchPayload'
    deleteOneRoomClick: 'RoomClick'
    deleteManyRoomClick: 'BatchPayload'
    upsertOneRoomClick: 'RoomClick'
    createOneRoomCredentials: 'RoomCredentials'
    updateOneRoomCredentials: 'RoomCredentials'
    updateManyRoomCredentials: 'BatchPayload'
    deleteOneRoomCredentials: 'RoomCredentials'
    deleteManyRoomCredentials: 'BatchPayload'
    upsertOneRoomCredentials: 'RoomCredentials'
  },
  Category: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    name: 'String'
    thumbnailUrl: 'String'
    rooms: 'Room'
  }
  Room: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    title: 'String'
    roomUrl: 'String'
    thumbnailUrl: 'String'
    roomCredentials: 'RoomCredentials'
    category: 'Category'
    categoryId: 'String'
    clicks: 'RoomClick'
    deletedAt: 'DateTime'
  }
  RoomClick: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    room: 'Room'
    roomId: 'String'
  }
  RoomCredentials: {
    id: 'String'
    createdAt: 'DateTime'
    updatedAt: 'DateTime'
    room: 'Room'
    roomId: 'String'
    password: 'String'
  }
}

// Helper to gather all methods relative to a model
interface NexusPrismaMethods {
  Category: Typegen.NexusPrismaFields<'Category'>
  Room: Typegen.NexusPrismaFields<'Room'>
  RoomClick: Typegen.NexusPrismaFields<'RoomClick'>
  RoomCredentials: Typegen.NexusPrismaFields<'RoomCredentials'>
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
  
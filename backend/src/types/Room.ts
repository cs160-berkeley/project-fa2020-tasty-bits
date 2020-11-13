import { objectType } from "@nexus/schema";

export const Room = objectType({
  name: "Room",
  definition(t) {
    t.model.id();
    t.model.createdAt();
    t.model.updatedAt();
    t.model.title();
    t.model.thumbnailUrl();
    t.model.roomUrl();
    t.model.roomCredentials();
    t.model.clicks();
    t.model.deletedAt();
  },
});

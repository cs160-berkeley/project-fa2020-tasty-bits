import { objectType } from "@nexus/schema";

export const RoomClick = objectType({
  name: "RoomClick",
  definition(t) {
    t.model.id();
    t.model.createdAt();
  },
});

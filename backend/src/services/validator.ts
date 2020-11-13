import { Context } from "nexus-plugin-prisma/dist/schema/utils";

export const validateRoomCredentials = async ({
  ctx,
  roomId,
  password,
}: {
  ctx: Context;
  roomId: string;
  password: string;
}) => {
  let currentRoom = await ctx.prisma.room.findOne({
    where: { id: roomId },
    include: { roomCredentials: true },
  });

  return password === currentRoom?.roomCredentials.password;
};

export const validateRoomLink = (link: string) => {
  let pathArray = link.split("/");
  let protocol = pathArray[0];
  let host = pathArray[2];
  let resource = pathArray[3];
  const accepted = [
    "zoom.us",
    "berkeley.zoom.us",
    "meet.google.com",
    "discord.com/invite",
  ];
  return accepted.some((h) => h === host || h === host + "/" + resource);
};

export const validateRoomTitle = (title: string) => {
  //prettier-ignore
  let banned = ['anal','anus','arse','ballsack','balls','bastard','bitch','biatch','blowjob','blow job','bollock','bollok','boner','boob','bugger','bum','buttplug','clitoris','cock','coon','cunt','dick','dildo','dyke','fag','feck','fellate','fellatio','felching','fudgepacker','fudge packer','flange','homo','jerk','jizz','knobend','knob end','labia','muff','nigger','nigga','penis','piss','poop','prick','pube','pussy','queer','scrotum','sex','slut','smegma','spunk','tit','tosser','turd','twat','vagina','wank','whore']
  return (
    banned.every((word) => !title.toLowerCase().includes(word)) &&
    title.trim().length > 0
  );
};

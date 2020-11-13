"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
exports.__esModule = true;
exports.validateRoomTitle = exports.validateRoomLink = exports.validateRoomCredentials = void 0;
exports.validateRoomCredentials = function (_a) {
    var ctx = _a.ctx, roomId = _a.roomId, password = _a.password;
    return __awaiter(void 0, void 0, void 0, function () {
        var currentRoom;
        return __generator(this, function (_b) {
            switch (_b.label) {
                case 0: return [4 /*yield*/, ctx.prisma.room.findOne({
                        where: { id: roomId },
                        include: { roomCredentials: true }
                    })];
                case 1:
                    currentRoom = _b.sent();
                    return [2 /*return*/, password === (currentRoom === null || currentRoom === void 0 ? void 0 : currentRoom.roomCredentials.password)];
            }
        });
    });
};
exports.validateRoomLink = function (link) {
    var pathArray = link.split("/");
    var protocol = pathArray[0];
    var host = pathArray[2];
    var resource = pathArray[3];
    var accepted = [
        "zoom.us",
        "berkeley.zoom.us",
        "meet.google.com",
        "discord.com/invite",
    ];
    return accepted.some(function (h) { return h === host || h === host + "/" + resource; });
};
exports.validateRoomTitle = function (title) {
    //prettier-ignore
    var banned = ['anal', 'anus', 'arse', 'ballsack', 'balls', 'bastard', 'bitch', 'biatch', 'blowjob', 'blow job', 'bollock', 'bollok', 'boner', 'boob', 'bugger', 'bum', 'buttplug', 'clitoris', 'cock', 'coon', 'cunt', 'dick', 'dildo', 'dyke', 'fag', 'feck', 'fellate', 'fellatio', 'felching', 'fudgepacker', 'fudge packer', 'flange', 'homo', 'jerk', 'jizz', 'knobend', 'knob end', 'labia', 'muff', 'nigger', 'nigga', 'penis', 'piss', 'poop', 'prick', 'pube', 'pussy', 'queer', 'scrotum', 'sex', 'slut', 'smegma', 'spunk', 'tit', 'tosser', 'turd', 'twat', 'vagina', 'wank', 'whore'];
    return (banned.every(function (word) { return !title.toLowerCase().includes(word); }) &&
        title.trim().length > 0);
};
//# sourceMappingURL=validator.js.map
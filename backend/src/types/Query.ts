import { idArg, queryType, stringArg } from '@nexus/schema';
import { getUserId } from '../utils';

const removeStopwords = (str: string): string => {
  //prettier-ignore
  const stopwords = [ "a", "able", "about", "above", "abst", "accordance", "according", "accordingly", "across", "act", "actually", "added", "adj", "affected", "affecting", "affects", "after", "afterwards", "again", "against", "ah", "all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "announce", "another", "any", "anybody", "anyhow", "anymore", "anyone", "anything", "anyway", "anyways", "anywhere", "apparently", "approximately", "are", "aren", "arent", "arise", "around", "as", "aside", "ask", "asking", "at", "auth", "available", "away", "awfully", "b", "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "begin", "beginning", "beginnings", "begins", "behind", "being", "believe", "below", "beside", "besides", "between", "beyond", "biol", "both", "brief", "briefly", "but", "by", "c", "ca", "came", "can", "cannot", "can't", "cause", "causes", "certain", "certainly", "co", "com", "come", "comes", "contain", "containing", "contains", "could", "couldnt", "d", "date", "did", "didn't", "different", "do", "does", "doesn't", "doing", "done", "don't", "down", "downwards", "due", "during", "e", "each", "ed", "edu", "effect", "eg", "eight", "eighty", "either", "else", "elsewhere", "end", "ending", "enough", "especially", "et", "et-al", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "except", "f", "far", "few", "ff", "fifth", "first", "five", "fix", "followed", "following", "follows", "for", "former", "formerly", "forth", "found", "four", "from", "further", "furthermore", "g", "gave", "get", "gets", "getting", "give", "given", "gives", "giving", "go", "goes", "gone", "got", "gotten", "h", "had", "happens", "hardly", "has", "hasn't", "have", "haven't", "having", "he", "hed", "hence", "her", "here", "hereafter", "hereby", "herein", "heres", "hereupon", "hers", "herself", "hes", "hi", "hid", "him", "himself", "his", "hither", "home", "how", "howbeit", "however", "hundred", "i", "id", "ie", "if", "i'll", "im", "immediate", "immediately", "importance", "important", "in", "inc", "indeed", "index", "information", "instead", "into", "invention", "inward", "is", "isn't", "it", "itd", "it'll", "its", "itself", "i've", "j", "just", "k", "keep	", "keeps", "kept", "kg", "km", "know", "known", "knows", "l", "largely", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "line", "little", "'ll", "look", "looking", "looks", "ltd", "m", "made", "mainly", "make", "makes", "many", "may", "maybe", "me", "mean", "means", "meantime", "meanwhile", "merely", "mg", "might", "million", "miss", "ml", "more", "moreover", "most", "mostly", "mr", "mrs", "much", "mug", "must", "my", "myself", "n", "na", "name", "namely", "nay", "nd", "near", "nearly", "necessarily", "necessary", "need", "needs", "neither", "never", "nevertheless", "next", "nine", "ninety", "no", "nobody", "non", "none", "nonetheless", "noone", "nor", "normally", "nos", "not", "noted", "nothing", "now", "nowhere", "o", "obtain", "obtained", "obviously", "of", "off", "often", "oh", "ok", "okay", "omitted", "on", "once", "one", "ones", "only", "onto", "or", "ord", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "owing", "own", "p", "page", "pages", "part", "particular", "particularly", "past", "per", "perhaps", "placed", "please", "plus", "poorly", "possible", "possibly", "potentially", "pp", "predominantly", "present", "previously", "primarily", "probably", "promptly", "proud", "provides", "put", "q", "que", "quickly", "quite", "qv", "r", "ran", "rather", "rd", "re", "readily", "really", "recent", "recently", "ref", "refs", "regarding", "regardless", "regards", "related", "relatively", "research", "respectively", "resulted", "resulting", "results", "right", "run", "s", "said", "same", "saw", "say", "saying", "says", "sec", "section", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sent", "seven", "several", "shall", "she", "shed", "she'll", "shes", "should", "shouldn't", "show", "showed", "shown", "showns", "shows", "significant", "significantly", "similar", "similarly", "since", "six", "slightly", "so", "some", "somebody", "somehow", "someone", "somethan", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specifically", "specified", "specify", "specifying", "still", "stop", "strongly", "sub", "substantially", "successfully", "such", "sufficiently", "suggest", "sup", "sure	", "t", "take", "taken", "taking", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "that'll", "thats", "that've", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "thered", "therefore", "therein", "there'll", "thereof", "therere", "theres", "thereto", "thereupon", "there've", "these", "they", "theyd", "they'll", "theyre", "they've", "think", "this", "those", "thou", "though", "thoughh", "thousand", "throug", "through", "throughout", "thru", "thus", "til", "tip", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "ts", "twice", "two", "u", "un", "under", "unfortunately", "unless", "unlike", "unlikely", "until", "unto", "up", "upon", "ups", "us", "use", "used", "useful", "usefully", "usefulness", "uses", "using", "usually", "v", "value", "various", "'ve", "very", "via", "viz", "vol", "vols", "vs", "w", "want", "wants", "was", "wasnt", "way", "we", "wed", "welcome", "we'll", "went", "were", "werent", "we've", "what", "whatever", "what'll", "whats", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "wheres", "whereupon", "wherever", "whether", "which", "while", "whim", "whither", "who", "whod", "whoever", "whole", "who'll", "whom", "whomever", "whos", "whose", "why", "widely", "willing", "wish", "with", "within", "without", "wont", "words", "world", "would", "wouldnt", "www", "x", "y", "yes", "yet", "you", "youd", "you'll", "your", "youre", "yours", "yourself", "yourselves", "you've", "z", "zero",]

  const res: string[] = [];
  const words = str.split(' ');
  for (let word of words) {
    let cleanWord = word.split('.').join('');
    if (!stopwords.includes(cleanWord)) {
      res.push(cleanWord);
    }
  }
  return res.join(' ');
};

const toTitleCase = (str: string) => {
  return str.replace(/\w\S*/g, function (txt) {
    return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
  });
};

const generateContains = (key: string, query: string) => {
  const allContains: {
    [x: string]: {
      contains: string;
    };
  }[] = [];

  removeStopwords(query)
    .split(' ')
    .forEach((word) => {
      allContains.push({
        [key]: {
          contains: word.toLowerCase(),
        },
      });
      allContains.push({
        [key]: {
          contains: toTitleCase(word),
        },
      });
      if (word !== word.toLowerCase() && word !== toTitleCase(word)) {
        allContains.push({
          [key]: {
            contains: word,
          },
        });
      }
    });

  return allContains;
};

export const Query = queryType({
  definition(t) {
    t.field('getUser', {
      type: 'User',
      nullable: true,
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;
        return ctx.prisma.user.findOne({
          where: {
            id: userId,
          },
        });
      },
    });

    t.list.field('getAnswers', {
      type: 'Answer',
      nullable: false,
      args: {
        questionId: idArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        return ctx.prisma.answer.findMany({
          where: {
            questionId: args.questionId,
            deletedAt: {
              equals: null,
            },
          },
          orderBy: {
            id: 'desc',
          },
          include: {
            votes: true,
          },
        });
      },
    });

    t.list.field('getQuestions', {
      type: 'Question',
      nullable: false,
      args: {
        categoryId: idArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        return ctx.prisma.question.findMany({
          where: {
            categories: {
              some: {
                id: args.categoryId,
              },
            },
            deletedAt: {
              equals: null,
            },
          },
          orderBy: {
            id: 'desc',
          },
          include: {
            votes: true,
            clicks: true,
            categories: true,
          },
        });
      },
    });

    t.list.field('getCategories', {
      type: 'Category',
      nullable: false,
      resolve: (_, args, ctx) => {
        return ctx.prisma.category.findMany({
          orderBy: {
            id: 'desc',
          },
        });
      },
    });

    t.list.field('getChatrooms', {
      type: 'Chatroom',
      nullable: false,
      resolve: (_, args, ctx) => {
        const userId = getUserId(ctx)!;

        return ctx.prisma.user
          .findOne({
            where: {
              id: userId,
            },
          })
          .chatrooms({
            orderBy: {
              id: 'desc',
            },
            include: {
              users: true,
            },
          });
      },
    });

    t.list.field('getMessages', {
      type: 'Message',
      nullable: false,
      args: {
        chatroomId: idArg({ required: true }),
      },
      resolve: (_, args, ctx) => {
        return ctx.prisma.chatroom
          .findOne({
            where: {
              id: args.chatroomId,
            },
          })
          .messages({
            orderBy: {
              id: 'desc',
            },
            include: {
              user: true,
            },
          });
      },
    });

    t.field('search', {
      type: 'SearchResults',
      nullable: false,
      args: {
        query: stringArg({ required: true }),
      },
      resolve: async (_, args, ctx) => {
        let [questions, answers, users] = await Promise.all([
          ctx.prisma.question.findMany({
            where: {
              OR: [
                ...generateContains('title', args.query),
                ...generateContains('description', args.query),
              ],
            },
          }),
          ctx.prisma.answer.findMany({
            where: {
              OR: [...generateContains('contents', args.query)],
            },
          }),
          ctx.prisma.user.findMany({
            where: {
              OR: [
                ...generateContains('name', args.query),
                ...generateContains('biography', args.query),
              ],
            },
          }),
        ]);

        return {
          answers,
          questions,
          users,
        };
      },
    });
  },
});

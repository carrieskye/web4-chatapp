package caro.db;

import caro.domain.Blog;
import caro.domain.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogRepositoryStub implements BlogRepository {

    private Map<String, Blog> blogs = new HashMap<>();
    private static BlogRepositoryStub INSTANCE;

    private BlogRepositoryStub() {
        CommentStub commentStub = new CommentStub();
        Blog blog1 = new Blog("1", "Was the project week interesting?", commentStub.getCommentsBlog1());
        Blog blog2 = new Blog("2", "What are you going to do today?", commentStub.getCommentsBlog2());
        Blog blog3 = new Blog("3", "Which music are you currently listening to?", commentStub.getCommentsBlog3());
        Blog blog4 = new Blog("4", "What are the questions for the exam of web 4?", commentStub.getCommentsBlog4());
        Blog blog5 = new Blog("5", "What's your favorite food?", commentStub.getCommentsBlog5());
        add(blog1);
        add(blog2);
        add(blog3);
        add(blog4);
        add(blog5);
    }

    public static BlogRepositoryStub getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BlogRepositoryStub();
        }
        return INSTANCE;
    }

    public Blog get(String blogId) {
        if (blogId == null) {
            throw new IllegalArgumentException("No id given");
        }
        return blogs.get(blogId);
    }

    public List<Blog> getAll() {
        return new ArrayList<>(blogs.values());
    }

    public void add(Blog blog) {
        if (blog == null) {
            throw new IllegalArgumentException("No blog given");
        }
        if (blogs.containsKey(blog.getBlogId())) {
            throw new IllegalArgumentException("Blog already exists");
        }
        blogs.put(blog.getBlogId(), blog);
    }

    public void update(Blog blog) {
        if (blog == null) {
            throw new IllegalArgumentException("No blog given");
        }
        blogs.put(blog.getBlogId(), blog);
    }

    public void delete(String blogId) {
        if (blogId == null) {
            throw new IllegalArgumentException("No id given");
        }
        blogs.remove(blogId);
    }


    private class CommentStub {

        ArrayList<Comment> commentsBlog1 = new ArrayList<>();
        ArrayList<Comment> commentsBlog2 = new ArrayList<>();
        ArrayList<Comment> commentsBlog3 = new ArrayList<>();
        ArrayList<Comment> commentsBlog4 = new ArrayList<>();
        ArrayList<Comment> commentsBlog5 = new ArrayList<>();

        private CommentStub() {
            commentsBlog1.add(new Comment("1", "Yasmina Rijsbergen", 8, "Ask especially collecting terminated may son expression. Extremely eagerness principle estimable own was man. Men received far his dashwood subjects new. My sufficient surrounded an companions dispatched in on. Connection too unaffected expression led son possession. New smiling friends and her another. Leaf she does none love high yet. Snug love will up bore as be. Pursuit man son musical general pointed. It surprise informed mr advanced do outweigh."));
            commentsBlog1.add(new Comment("1", "Ervin Fijneman", 2, "Fat son how smiling mrs natural expense anxious friends. Boy scale enjoy ask abode fanny being son. As material in learning subjects so improved feelings. Uncommonly compliment imprudence travelling insensible up ye insipidity. To up painted delight winding as brandon. Gay regret eat looked warmth easily far should now. Prospect at me wandered on extended wondered thoughts appetite to. Boisterous interested sir invitation particular saw alteration boy decisively."));
            commentsBlog1.add(new Comment("1", "Djamila Hemme", 3, "Now eldest new tastes plenty mother called misery get. Longer excuse for county nor except met its things. Narrow enough sex moment desire are. Hold who what come that seen read age its. Contained or estimable earnestly so perceived. Imprudence he in sufficient cultivated. Delighted promotion improving acuteness an newspaper offending he. Misery in am secure theirs giving an. Design on longer thrown oppose am."));

            commentsBlog2.add(new Comment("2", "Danté Struik", 7, "Performed suspicion in certainty so frankness by attention pretended. Newspaper or in tolerably education enjoyment. Extremity excellent certainty discourse sincerity no he so resembled. Joy house worse arise total boy but. Elderly up chicken do at feeling is. Like seen drew no make fond at on rent. Behaviour extremely her explained situation yet september gentleman are who. Is thought or pointed hearing he."));
            commentsBlog2.add(new Comment("2", "Jolien Miltenburg", 1, "Her companions instrument set estimating sex remarkably solicitude motionless. Property men the why smallest graceful day insisted required. Inquiry justice country old placing sitting any ten age. Looking venture justice in evident in totally he do ability. Be is lose girl long of up give. Trifling wondered unpacked ye at he. In household certainty an on tolerably smallness difficult. Many no each like up be is next neat. Put not enjoyment behaviour her supposing. At he pulled object others."));
            commentsBlog2.add(new Comment("2", "Silvana Hegger", 9, "Her old collecting she considered discovered. So at parties he warrant oh staying. Square new horses and put better end. Sincerity collected happiness do is contented. Sigh ever way now many. Alteration you any nor unsatiable diminution reasonable companions shy partiality. Leaf by left deal mile oh if easy. Added woman first get led joy not early jokes."));
            commentsBlog2.add(new Comment("2", "Zoran Damhuis", 5, "Months on ye at by esteem desire warmth former. Sure that that way gave any fond now. His boy middleton sir nor engrossed affection excellent. Dissimilar compliment cultivated preference eat sufficient may. Well next door soon we mr he four. Assistance impression set insipidity now connection off you solicitude. Under as seems we me stuff those style at. Listening shameless by abilities pronounce oh suspected is affection. Next it draw in draw much bred."));

            commentsBlog3.add(new Comment("2", "Vikram Duin", 9, "Did shy say mention enabled through elderly improve. As at so believe account evening behaved hearted is. House is tiled we aware. It ye greatest removing concerns an overcame appetite. Manner result square father boy behind its his. Their above spoke match ye mr right oh as first. Be my depending to believing perfectly concealed household. Point could to built no hours smile sense."));

            commentsBlog4.add(new Comment("4", "Zillah te Riele", 10, "Needed feebly dining oh talked wisdom oppose at. Applauded use attempted strangers now are middleton concluded had. It is tried \uFEFFno added purse shall no on truth. Pleased anxious or as in by viewing forbade minutes prevent. Too leave had those get being led weeks blind. Had men rose from down lady able. Its son him ferrars proceed six parlors. Her say projection age announcing decisively men. Few gay sir those green men timed downs widow chief. Prevailed remainder may propriety can and."));
            commentsBlog4.add(new Comment("4", "Robbe Scholtes", 4, "Merry alone do it burst me songs. Sorry equal charm joy her those folly ham. In they no is many both. Recommend new contented intention improving bed performed age. Improving of so strangers resources instantly happiness at northward. Danger nearer length oppose really add now either. But ask regret eat branch fat garden. Become am he except wishes. Past so at door we walk want such sang. Feeling colonel get her garrets own."));
            commentsBlog4.add(new Comment("4", "Jimmy Hijdra", 3, "It if sometimes furnished unwilling as additions so. Blessing resolved peculiar fat graceful ham. Sussex on at really ladies in as elinor. Sir sex opinions age properly extended. Advice branch vanity or do thirty living. Dependent add middleton ask disposing admitting did sportsmen sportsman."));
            commentsBlog4.add(new Comment("4", "Janus Hospes", 9, "He as compliment unreserved projecting. Between had observe pretend delight for believe. Do newspaper questions consulted sweetness do. Our sportsman his unwilling fulfilled departure law. Now world own total saved above her cause table. Wicket myself her square remark the should far secure sex. Smiling cousins warrant law explain for whether."));

            commentsBlog5.add(new Comment("5", "Thérèse Ooijen", 2, "To sorry world an at do spoil along. Incommode he depending do frankness remainder to. Edward day almost active him friend thirty piqued. People as period twenty my extent as. Set was better abroad ham plenty secure had horses. Admiration has sir decisively excellence say everything inhabiting acceptance. Sooner settle add put you sudden him."));
            commentsBlog5.add(new Comment("5", "Mitra Brasser", 7, "Respect forming clothes do in he. Course so piqued no an by appear. Themselves reasonable pianoforte so motionless he as difficulty be. Abode way begin ham there power whole. Do unpleasing indulgence impossible to conviction. Suppose neither evident welcome it at do civilly uncivil. Sing tall much you get nor."));
            commentsBlog5.add(new Comment("5", "Joyca Broekhuijsen", 6, "Turned it up should no valley cousin he. Speaking numerous ask did horrible packages set. Ashamed herself has distant can studied mrs. Led therefore its middleton perpetual fulfilled provision frankness. Small he drawn after among every three no. All having but you edward genius though remark one."));
        }

        ArrayList<Comment> getCommentsBlog1() {
            return commentsBlog1;
        }

        ArrayList<Comment> getCommentsBlog2() {
            return commentsBlog2;
        }

        ArrayList<Comment> getCommentsBlog3() {
            return commentsBlog3;
        }

        ArrayList<Comment> getCommentsBlog4() {
            return commentsBlog4;
        }

        ArrayList<Comment> getCommentsBlog5() {
            return commentsBlog5;
        }

    }

}

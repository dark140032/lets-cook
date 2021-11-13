package com.example.letscook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME = "LetCook.sqlite";
    private  static final int DATABASE_VERSION = 1;

    public static final String TBL_NOTE = "note";
    public  static final String NOTE_ID = "note_id";
    public  static final String NOTE_NAME = "note_name";
    public  static final String NOTE_CONTENT = "note_contnt";
    public  static final String FIRT_REGISTER_PTTM= "frstRegistPttm";

    public String TBL_CREATE_NOTE = " create table " + TBL_NOTE + " (" +
            NOTE_ID + " integer primary key ," +
            NOTE_NAME + " text UNIQUE ," +
            NOTE_CONTENT + " text ," +
            FIRT_REGISTER_PTTM + " text ,"+
            USER_ID + " INTEGER ," +
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    public static final String TBL_USER = "user";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_AVATAR = "user_avatar";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String JOB = "job";
    public static final String USER_DESCRIPTION = "user_description";

    public String TBL_CREATE_USER = " create table " + TBL_USER + " (" +
            USER_ID + " integer primary key AUTOINCREMENT ," +
            USER_NAME + " TEXT NOT NULL ," +
            EMAIL + " TEXT NOT NULL UNIQUE ," +
            PASSWORD + " TEXT NOT NULL , " +
            USER_AVATAR + " TEXT ," +
            DATE_OF_BIRTH + " text ,"+
            JOB + " TEXT ," +
            USER_DESCRIPTION + " TEXT )";

    public static final String TBL_WISHLIST = "wishlist";
    public static final String WISHLIST_ID = "wishlist_id";

    public String TBL_CREATE_WISHLIST = "create table " + TBL_WISHLIST + " (" +
            WISHLIST_ID + " integer primary key AUTOINCREMENT," +
            USER_ID + " INTEGER ,"+
            RECIPE_ID + " INTEGER ," +
            " FOREIGN KEY(" + RECIPE_ID + ") REFERENCES "+ TBL_RECIPE + "(" + RECIPE_ID + "),"+
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    public static final String TBL_RECIPE = "recipe";
    public static final String RECIPE_ID = "recipe_id";
    public static final String RECIPE_NAME = "recipe_name";
    public static final String RECIPE_DES = "recipe_des";
    public static final String RECIPE_MATERIAL = "recipe_material";
    public static final String RECIPE_MAKING = "recipe_making";
    public static final String RECIPE_AVATAR = "recipe_avatar";

    public String TBL_CREATE_RECIPE= "create table " + TBL_RECIPE + " (" +
            RECIPE_ID + " integer primary key AUTOINCREMENT," +
            RECIPE_NAME + " TEXT NOT NULL ,"+
            RECIPE_DES + " text NOT NULL ,"+
            RECIPE_MATERIAL + " text NOT NULL ,"+
            RECIPE_MAKING + " text NOT NULL ,"+
            RECIPE_AVATAR + " TEXT NOT NULL )";

    public static final String TBL_THEME = "theme";
    public static final String THEME_ID = "theme_id";
    public static final String THEME_NAME = "theme_name";
    public static final String THEME_IMAGE = "theme_image";

    public String TBL_CREATE_THEME= "create table " + TBL_THEME + " (" +
            THEME_ID + " integer primary key AUTOINCREMENT," +
            THEME_NAME + " TEXT UNIQUE," +
            THEME_IMAGE + " TEXT)";

    public static final String TBL_THEME_RECIPE = "theme_recipe";

    public String TBL_CREATE_THEME_RECIPE= "create table " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " integer, " +
            RECIPE_ID + " integer, "+
            " CONSTRAINT " +  TBL_THEME_RECIPE + " PRIMARY KEY (" + THEME_ID+ "," + RECIPE_ID +  "))";

    public static final String TBL_CATEGORY = "category";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    public String TBL_CREATE_CATEGORY= "create table " + TBL_CATEGORY + " (" +
            CATEGORY_ID + " integer primary key AUTOINCREMENT," +
            CATEGORY_NAME + " TEXT UNIQUE)";

    public static final String TBL_CATEGORY_RECIPE = "category_recipe";

    public String TBL_CREATE_CATEGORY_RECIPE= "create table " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " integer ," +
            RECIPE_ID + " integer ,"+
            " CONSTRAINT " +  TBL_CATEGORY_RECIPE + " PRIMARY KEY (" + CATEGORY_ID+ "," + RECIPE_ID +  "))";

    private String QUE_INSERT_USER= "insert into " + TBL_USER + " (" +
            USER_NAME + " , " +
            EMAIL + " , " +
            PASSWORD + " , " +
            USER_AVATAR + " , " +
            DATE_OF_BIRTH + " , " +
            JOB + " , " +
            USER_DESCRIPTION + " )" +
            "VALUES ( " +
            "'DUC', 'DUC', 'DUC', 'DUC', 'DUC', 'DUC', 'DUC' " +
            ")";

    private String QUE_INSERT_RECIPE= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Pizza hải sản', 'Pizza hải sản là món dễ ăn, thơm ngon và được nhiều người yêu thích', 'Tôm sú                300 gr\n" +
            "Mực ống" +
            "              200 gr\n" +
            "Ớt chuông " +
            "              2 trái\n" +
            "Phô mai Mozzarella \n" +
            "Tương cà \n" +
            "Đế bánh Pizza\n" +
            "Hành tím \n" +
            "Bơ ', 'hambeger', 'pizzahaisan' " +
            ")";
    private String QUE_INSERT_RECIPE1= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Hamburger Bò', 'Hamburger là món ăn nhanh được nhiều bạn trẻ yêu thích và lựa chọn là món ăn sáng hàng đầu bởi sự nhanh chóng và tiện lợi.', " +
            "'Bò băm 500 gr\n" +
            " Đường 2 muỗng cà phê\n" +
            " Hạt nêm 3 muỗng cà phê\n" +
            " Muối 1/2 muỗng cà phê\n" +
            " Tiêu 2 muỗng cà phê\n" +
            " Bột chiên xù 1/2 chén\n" +
            " Dầu ăn 1 muỗng canh\n" +
            " Bánh mì burger 8 cái\n" +
            " Phô mai miếng 8 lát\n" +
            "Bơ ', 'Ướp thịt bò\n" +
            "rộn đều thịt bò băm với các loại gia vị gồm 2 muỗng cà phê đường, 3 muỗng cà phê hạt nêm, 1/2 muỗng cà phê muối, 1/2 chén bột chiên xù và 1 muỗng canh dầu ăn. Sau đó, ướp trong vòng 15 phút.\n" +
            "Vo thịt thành từng viên tròn, đem chiên chín. Khi chiên ấn cho viên thịt tròn dẹt xuống. Có thể cho phô mai bào sợi lên nếu thích.', 'hamburgerbo' " +
            ")";

    private String QUE_INSERT_RECIPE2= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Hamburger Gà', 'Burger gà là một trong những món ăn nhanh rất tiện lợi được nhiều người ưa chuộng. Cùng tham khảo công thức làm bánh hamburger gà mới lạ dưới đây để có những chiếc bánh cực ngon.', " +
            "'500 gram ức gà\n" +
            "2 quả trứng gà\n" +
            "40 gram vụn bánh mì\n" +
            "4 chiếc vỏ bánh hamburger\n" +
            "Hành lá, hành khô, tỏi\n" +
            "Dưa chuột, rau xà lách, cà chua\n" +
            "Bơ ', 'Ngâm thịt gà cùng 1 thìa muối hạt và 1 ít gừng để khử sạch mùi hôi. Rửa sạch gà với nước sạch, vớt ra để ráo nước rồi cho vào máy xay xay nhỏ. Nếu không có máy xay bạn cũng có thể cho thịt ra thớt và dùng dao băm.\n" +
            "Hành lá rửa sạch, thái nhỏ.\n" +
            "Hành khô, tỏi bóc vỏ, băm nhuyễn.\n" +
            "Dưa chuột ngâm một chút muối, nạo vỏ, thái lát mỏng.\n" +
            "Rau xà lách rửa qua bằng nước muối, vớt ra rổ để ráo nước rồi tách thành từng lá.\n" +
            "Cà chua rửa sạch, thái thành lát mỏng.', 'hamburgerga' " +
            ")";

    private String QUE_INSERT_RECIPE3= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Phở Gà', 'Phở là món ăn truyền thống và có thể nói là đại diện cho cả nền ẩm thực của mảnh đất chữ S. Một trong số đó tất nhiên không thể bỏ qua món phở gà siêu hấp dẫn được rồi!', " +
            "'Bánh phở tươi 200 gr\n" +
            " Xương heo 1.5 kg\n" +
            " Gà ta 1.6 kg\n" +
            " Trứng non 100 gr\n" +
            " Củ cải trắng 100 gr\n" +
            " Hành tây 150 gr\n" +
            "(3 củ)\n" +
            " Ngò rí 20 gr\n" +
            " Hành lá 1 cây\n" +
            " Hành tím 5 củ\n" +
            " Gừng 1 củ\n" +
            " Gia vị thuốc bắc 1 gói\n" +
            " Chanh 1 trái\n" +
            " Nước mắm 2 muỗng canh\n" +
            " Hạt nêm gà 3 muỗng canh\n" +
            " Đường phèn 4 muỗng canh\n" +
            " Muối/ Bột ngọt 1 ít\n\n" +
            "Bơ ', 'Sơ chế và hầm xương\n" +
            "Đầu tiên, xương heo mua về bạn ngâm với nước muối pha loãng 5 - 7 phút. Kế đến, đem rửa lại vài lần với nước sạch, để ráo.\n" +
            "\n" +
            "Sau đó, bắc nồi lên bếp cùng 1 lít nước với lửa lớn. Nước lăn tăn sôi thì bạn cho toàn bộ phần xương heo đã sơ chế vào, tiến hành chần sơ khoảng 3 - 5 phút.\n" +
            "\n" +
            "Thấy xương heo hơi săn lại thì bạn tắt bếp, đem xương heo rửa trực tiếp dưới vòi nước lạnh rồi để ráo.', 'phoga' " +
            ")";


    private String QUE_INSERT_RECIPE4= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Phở Bò', 'Không phải ngẫu nhiên phở lại được các đầu bếp hàng đầu thế giới bình chọn là món ăn nên thử ít nhất 1 lần trong đời. Đằng sau mỗi tô phở ấy là một hương vị đặc trưng khó mà lẫn lộn.', " +
            "'Bò phi lê 1.2 kg\n" +
            " Xương ống bò 2 kg\n" +
            " Bắp bò 0.7 kg\n" +
            " Hoa hồi 50 gr\n" +
            " Bánh phở 1 kg\n" +
            " Đinh hương 100 gr\n" +
            " Thảo quả 100 gr\n" +
            " Hạt ngò 50 gr\n" +
            " Giá sống 300 gr\n" +
            " Sả 2 nhánh\n" +
            " Hành tây 1 củ\n" +
            " Vỏ quýt 100 gr\n" +
            " Rau ăn kèm 1 ít\n" +
            "(ngò ôm/ngò gai)\n" +
            " Gừng 1 củ\n" +
            " Gia vị thông dụng 1 ít\n" +
            "Bơ ', 'Sơ chế nguyên liệu hầm nước dùng\n" +
            "Cho sả cây vào nồi nước lớn, đun sôi sau đó cho xương ống bò vào chần qua một lần để khử mùi hôi của xương bò.\n" +
            "\n" +
            "Tiếp đó cho xương ống bò vào khay nướng cùng với gừng và hành tây, rồi đặt khay vào lò nướng khoảng 5 đến 10 phút cho đến khi xương ống bò xém cạnh thì gắp xương ống bò thả vào thau nước đá.\n" +
            "\n" +
            "Tiếp đến, trải một tấm khăn mùng sạch ra mặt phẳng, cho thảo quả, vỏ quýt, đinh hương, hoa hồi và hạt ngò vào giữa tấm khăn. Bạn túm các mép khăn lại bọc kín nguyên liệu sau đó cột túm bằng một sợi dây chỉ.', 'phobo' " +
            ")";


    private String QUE_INSERT_RECIPE5= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Hamburger Than Tre', 'Bánh hamburger tinh than tre cuốn hút với sắc đen lạ mắt, vỏ bánh thơm nhẹ mùi bơ, ăn kèm cùng nhân thịt bò đậm vị, rau củ tươi mát, phô mai béo ngậy, cực kỳ bổ dưỡng.', " +
            "'Bột mì số 13 200 gr\n" +
            " Bột tinh than tre 10 gr\n" +
            " Bơ lạt 20 gr\n" +
            " Thịt bò xay 400 gr\n" +
            " Men instant 4 gr\n" +
            " Phô mai cheddar 4 miếng\n" +
            " Hành tây 1 củ\n" +
            " Cà chua 1 quả\n" +
            " Xà lách 100 gr\n" +
            " Dầu ăn 1 ít\n" +
            " Hạt mè trắng 1 ít\n" +
            " Sữa tươi không đường 140 ml\n" +
            " Nước sốt 1 ít\n" +
            "(mayonnaise/mù tạt vàng/tương cà/tương ớt)\n" +
            " Gia vị thông dụng 1 ít\n" +
            "(đường/muối/tiêu/bột ngọt)\n" +
            "Bơ ', 'Nhào bột bánh\n" +
            "Cho bột ra bàn và dùng tay nhào bột theo kỹ thuật Folding and Strectching.\n" +
            "\n" +
            "Đầu tiên, bạn gấp bột lại, sau đó dùng mu bàn ấn và miết bột ra xa. Lưu ý là ấn và miết bột ra xa chứ không phải ấn xuống. Kế tiếp xoay khối bột một góc 90 độ rồi lặp lại hai bước trên trong 15 phút.\n" +
            "\n" +
            "Cách nhận biết bột đạt yêu cầu:\n" +
            "\n" +
            "Bột dẻo mịn, có độ đàn hồi tốt.\n" +
            "Bột không dính tay: Khi nhấn vào cảm thấy hơi dính, nhưng khi nhấc ngón tay ra thì bột không dính tay.\n" +
            "Có thể kéo dãn bột thành màn mỏng mà không bị rách.\n" +
            "Kiểm tra bột bằng Windowpane. Ngắt một phần bột, kéo dãn bột ra. Nếu bột tạo thành màng mỏng, không dễ bị rách ánh sáng có thể đi xuyên qua là đạt.', 'hamburgerthantre' " +
            ")";

    private String QUE_INSERT_RECIPE6= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Hamburger Cá Hồi', 'Cá hồi vốn là một nguồn thức ăn giàu Omega - 3, vitamin B và chứa những chất dinh dưỡng đặc biệt như kali giúp làm giảm huyết áp, selenium giúp giảm nguy cơ mắc bệnh ung thư và astaxanthin chống oxy hóa.', " +
            "'100g cá hồi phi lê\n" +
            "1 củ hành tây\n" +
            "Xà lách\n" +
            "1 trái cà chua\n" +
            "1 chiếc bánh burger\n" +
            "1 lát phô mai Cheddar\n" +
            "Ngò tây\n" +
            "Sốt Mayonnaise, tương cà\n" +
            "Bơ, chanh, gia vị và bột tẩm chiên giòn\n" +
            "Bơ ', 'Cắt đôi chiếc bánh burger thành 2 lát và áp chảo, cho bơ vào sau để bánh mì giữ được độ ngon và lật đều. Cắt khoanh tròn hành tây và áp chảo để hành thơm, ngọt\n" +
            "\n" +
            "Rửa sạch xà lách, ngò tây và để ráo, sau đó cắt lát cà chua.\n" +
            "\n" +
            "Cắt lát 100g cá hồi dày tầm 1cm và tẩm ướp một ít tiêu, muối và nước cốt chanh.', 'hamburgercahoi' " +
            ")";

    private String QUE_INSERT_RECIPE7= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Pizza Phô Mai', 'Pizza phô mai mang trong mình một vị béo đặc trưng đến từ các loại phô mai Ý, khi dùng bánh pizza phô mai, ấn tượng đầu tiên của các bạn chắc chắn là phần phô mai rất đặc biệt. Ngoài ra bánh pizza phô mai có thể kết hợp với rất nhiều rau củ quả khác nhau tạo ra 1 món ăn không những ngon về phần vị giác mà còn rất tốt cho sức khỏe lâu dài nữa nè.', " +
            "'180gr bột mì \n" +
            "2gr men khô \n" +
            "2gr muối\n" +
            "15gr đường\n" +
            "15ml dầu olive\n" +
            "100ml nước ấm \n" +
            "Bơ ', 'Các bạn chuẩn bị 1 tô vừa, cho bột mì, men nở khô, 2gr muối, 15gr đường và 15ml dầu vào trộn đều trước, sau đó khi hỗn hợp quyện vào nhau các bạn mới cho thêm 100ml nước ấm. Các bạn chỉ cần đổ từ từ vào hỗn hợp cho đến khi bột trở thành 1 khối bột dẻo.\n" +
            "Lấy bột ra rồi trải 1 lớp bột áo, sau đó cán bánh ra sao cho phần viền bánh dày hơn bên trong bánh rồi cho tương cà vào quét thành 1 lớp rồi cho phần nhân vào bao gồm: xúc xích hun khói, 50gr phô mai, các bạn có thể thêm 1 trái trứng gà để giúp bánh trở nên béo hơn nha.', 'pizzaphomai' " +
            ")";

    private String QUE_INSERT_RECIPE8= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Pizza nấm', 'Nấm là một loại nguyên liệu quen thuộc trong ẩm thực đồ chay. Với các món chiên, xào truyền thống được chế biến từ nấm, món pizza nấm chay dưới đây cũng sẽ là một lựa chọn thú vị trong cách làm món chay từ nguyên liệu này.', " +
            "'Đế bánh Pizza : 1 chiếc\n" +
            "Nấm rơm: 50gr\n" +
            "Nấm đùi gà: 100gr\n" +
            "Nấm kim châm: 50gr\n" +
            "Cà chua: 2 quả\n" +
            "Phô mai Mozzarella tươi: 50gr\n" +
            "Tỏi\n" +
            "Tương cà, tương ớt.\n" +
            "Gia vị : Bột nêm chay, dầu oliu, oregano\n" +
            "Bơ ', '- Nấm rơm, nấm đùi gà, nấm kim châm bỏ rễ và cuống, đem rửa sạch, thái lát mỏng vừa ăn. Cà chua cũng rửa sạch, thái lát. Phô mai bào nhỏ. Tỏi bóc vỏ băm nhuyễn. Đem cà chua luộc qua, bóc vỏ rồi cho vào máy xay nhuyễn.\n" +
            "\n" +
            "- Bắc chảo lên bếp cho 1 thìa cafe dầu olive vào, đun nóng dầu, phi thơm tỏi. Lần lượt cho các loại nấm vào đảo qua, thêm gia vị cho hợp khẩu vị, đảo vài lần để ngấm sau đó tắt bếp.\n" +
            "\n" +
            "- Lấy đế pizza đã chuẩn bị, cho nguyên liệu lần lượt theo thứ tự: Sốt cà chua dưới đều trên bề mặt của đế bánh, lớp phô mai, nấm trải đều trên phô mai bề mặt bánh và rắc đều oregano lên trên.\n" +
            "\n" +
            "- Nướng Pizza ở nhiệt độ 200 độ C, trong 10 phút bánh chín bày ra đĩa cắt thành 6 miếng đều nhau. Pizza ngon nhất khi ăn kèm tương cà và tương ớt.', 'pizzanam' " +
            ")";

       private String QUE_INSERT_RECIPE9= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Pizza Xúc Xích', 'Pizza thơm lừng nóng hổi, lớp phô mai béo ngậy cùng xúc xích dai dai, đậm vị. Nếu thích, bạn có thể ăn kèm thêm với nhiều tương ớt và sốt mayonnaise cũng sẽ rất ngon đó.', " +
            "'Bột mì 110 gr\n" +
               " Muối 2 gr\n" +
               " Men nở 5 gr\n" +
               " Dầu ô-liu 12 ml\n" +
               " Nước 75 ml\n" +
               " Mật ong 10 gr\n" +
               " Sốt cà chua 50 gr\n" +
               " Xúc xích 200 gr\n" +
               " Phô mai mozzarella 100 gr\n" +
               " Sốt mayonnaise 50 gr\n" +
               " Tương ớt 50 gr\n" +
            "Bơ ', 'Sau khi trộn bột, bạn dùng máy để nhào bột được mịn hơn.\n" +
               "\n" +
               "Trong trường hợp không có máy nhào bột, bạn dùng tay gấp bột lại sau đó dùng mu bàn tay, ấn và miết, đẩy bột ra xa. Lưu ý là ấn và miết bột ra xa chứ không phải là ấn xuống, không ấn mà miết bột xuống sẽ làm sợi gluten trong bột đứt gãy, bánh sẽ không ngon. Xoay khối bột một góc 90 độ, lặp lại hai bước như trên.\n" +
               "\n" +
               "Sau 15 - 20 phút nhào bột, bạn ấn vào bột thấy vết lõm phồng trở lại và bột không dính tay, bột mịn màng, có độ đàn hồi tốt là bột đã đạt. Cuối cùng, bạn đậy kín và để bột nghỉ 1 giờ.\n" +
               "\n" +
               "Mách bạn: Khoảng 5 phút đầu, bột còn nhão và ướt, tuy nhiên bạn không được thêm bột mà chỉ cần áo một lớp một lớp bột mỏng bên ngoài là có thể dễ dàng nhào bột.', 'pizzaxucxich' " +
            ")";

       private String QUE_INSERT_RECIPE10= "insert into " + TBL_RECIPE + " (" +
            RECIPE_NAME + " , " +
            RECIPE_DES + " , " +
            RECIPE_MATERIAL + " , " +
            RECIPE_MAKING + " , " +
            RECIPE_AVATAR + " ) " +
            "VALUES ( " +
            "'Phở Xương Heo', 'Công thức nấu phở từ xương heo vô cùng thơm ngon và hấp dẫn tại nhà nhé. Nước dùng thanh ngọt và đậm đà, cùng vào bếp thực hiện món nước hấp dẫn này nhé!', " +
            "'Bánh phở 200 gr\n" +
               "(có thể sử dụng loại phở ăn liền hay phở khô)\n" +
               " Xương ống heo 400 gr\n" +
               " Thịt bò 300 gr\n" +
               " Gừng 1 củ\n" +
               " Hành tím 3 củ\n" +
               " Quế 1 nhánh\n" +
               " Hoa hồi 1 cái\n" +
               " Thảo quả 10 gr\n" +
               " Gia vị nấu phở nêm sẵn 1 gói\n" +
               " Hành lá 1 muỗng canh\n" +
               "(cắt nhỏ)\n" +
               " Rau ăn kèm 500 gr\n" +
               "(ngò gai/quế/ngò om/...)\n" +
               " Rượu trắng 1 muỗng cà phê\n" +
               " Nước mắm 1 muỗng canh\n" +
               " Gia vị thông dụng 1 ít\n" +
               "(Đường/ hạt nêm/ muối)\n" +
            "Bơ ', 'Thịt bò mua về bạn dùng 1 muỗng cà phê rượu trắng chà xát lên miếng thịt rồi rửa sơ lại với nước sạch, sau đó cho ra rổ để ráo nước.\n" +
               "\n" +
               "Đặt thịt bò lên 1 cái thớt sạch rồi dùng dao bén cắt theo chiều ngang của thớ thịt để thịt bò không bị dai khi ăn.\n" +
               "\n" +
               "Các loại rau ăn kèm bạn đem rửa với nước muối loãng, rồi rửa lại với nước sạch sau đó cho ra rổ để ráo nước là được.\n" +
               "\n" +
               "Gừng không cần cạo vỏ mà chỉ cần đập dập. Củ hành tím thì bỏ vỏ và để nguyên để nấu nước dùng.\n" +
               "\n" +
               "Mách bạn:\n" +
               "\n" +
               "Để cắt được miếng thịt bò ngon, bạn cần phải dùng dao thật bén và cắt thật dứt khoát theo chiều ngang của thớ thịt.\n" +
               "Cắt đi cắt lại nhiều lần sẽ khiến miếng thịt không ngon và không được đẹp mắt.\n" +
               "Cách khử mùi hôi thịt bò\n" +
               "\n" +
               "Bạn có thể mang 1 củ gừng đi nướng sơ qua rồi giã nhuyễn, chà xát tất cả gừng trên phần thịt bò một cách nhẹ nhàng, rửa lại với nước sạch là được.\n" +
               "Bằng cách ngâm miếng thịt bò trong rượu trắng hoặc nước vo gạo khoảng 10 - 15 phút, sau đó rửa lại sạch với nước lạnh.', 'bungioheo' " +
            ")";


    private String QUE_INSERT_WISHLIST= "insert into " + TBL_WISHLIST + " (" +
            USER_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "'1', '1'" +
            ")";
    private String QUE_INSERT_WISHLIST1= "insert into " + TBL_WISHLIST + " (" +
            USER_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "'1', '2'" +
            ")";

    private String QUE_INSERT_THEME= "insert into " + TBL_THEME + " (" +
            THEME_NAME + " , " +
            THEME_IMAGE + " ) " +

            "VALUES ( " +
            "'Ăn Tối', 'buatoi' )";

    private String QUE_INSERT_THEME1= "insert into " + TBL_THEME + " (" +
            THEME_NAME + " , " +
            THEME_IMAGE + " ) " +

            "VALUES ( " +
            "'Ăn Sáng' , 'buasang')";

    private String QUE_INSERT_THEME2= "insert into " + TBL_THEME + " (" +
            THEME_NAME + " , " +
            THEME_IMAGE + " ) " +

            "VALUES ( " +
            "'Ăn Trưa' , 'buatrua')";

    private String QUE_INSERT_THEME_RECIPE= "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 1)";

    private String QUE_INSERT_THEME_RECIPE1 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 2)";

    private String QUE_INSERT_THEME_RECIPE2 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 3)";

    private String QUE_INSERT_THEME_RECIPE3 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 4)";

    private String QUE_INSERT_THEME_RECIPE4 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 5)";

    private String QUE_INSERT_THEME_RECIPE5 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 6)";

    private String QUE_INSERT_THEME_RECIPE6 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 7)";

    private String QUE_INSERT_THEME_RECIPE7 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 8)";

    private String QUE_INSERT_THEME_RECIPE8 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 9)";

    private String QUE_INSERT_THEME_RECIPE9 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 10)";

    private String QUE_INSERT_THEME_RECIPE10 = "insert into " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 11)";



    private String QUE_INSERT_CATEGORY= "insert into " + TBL_CATEGORY + " (" +
            CATEGORY_NAME + " ) " +

            "VALUES ( " +
            "'Phở'" +
            ")";

    private String QUE_INSERT_CATEGORY1= "insert into " + TBL_CATEGORY + " (" +
            CATEGORY_NAME + " ) " +

            "VALUES ( " +
            "'Pizza'" +
            ")";

    private String QUE_INSERT_CATEGORY2= "insert into " + TBL_CATEGORY + " (" +
            CATEGORY_NAME + " ) " +

            "VALUES ( " +
            "'Hamburger'" +
            ")";

    private String QUE_INSERT_CATEGORY_RECIPE = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 1)";
    private String QUE_INSERT_CATEGORY_RECIPE1 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 2)";
    private String QUE_INSERT_CATEGORY_RECIPE2 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 3)";

    private String QUE_INSERT_CATEGORY_RECIPE3 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 4)";
    private String QUE_INSERT_CATEGORY_RECIPE4 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 5)";
    private String QUE_INSERT_CATEGORY_RECIPE5 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 6)";

    private String QUE_INSERT_CATEGORY_RECIPE6 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "3 , 7)";

    private String QUE_INSERT_CATEGORY_RECIPE7 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 8)";

    private String QUE_INSERT_CATEGORY_RECIPE8 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 9)";

    private String QUE_INSERT_CATEGORY_RECIPE9 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "2 , 10)";

    private String QUE_INSERT_CATEGORY_RECIPE10 = "insert into " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " , " +
            RECIPE_ID + " ) " +

            "VALUES ( " +
            "1 , 11)";

    public MyDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("okie", "MyDB:" );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE_NOTE);
        sqLiteDatabase.execSQL(TBL_CREATE_USER);
        sqLiteDatabase.execSQL(TBL_CREATE_WISHLIST);
        sqLiteDatabase.execSQL(TBL_CREATE_RECIPE);
        sqLiteDatabase.execSQL(TBL_CREATE_THEME_RECIPE);
        sqLiteDatabase.execSQL(TBL_CREATE_THEME);
        sqLiteDatabase.execSQL(TBL_CREATE_CATEGORY);
        sqLiteDatabase.execSQL(TBL_CREATE_CATEGORY_RECIPE);
        sqLiteDatabase.execSQL(QUE_INSERT_USER);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE1);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE2);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE3);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE4);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE5);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE6);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE7);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE8);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE9);
        sqLiteDatabase.execSQL(QUE_INSERT_RECIPE10);
        sqLiteDatabase.execSQL(QUE_INSERT_WISHLIST);
        sqLiteDatabase.execSQL(QUE_INSERT_WISHLIST1);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME1);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME2);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE1);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE2);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE3);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE4);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE5);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE6);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE7);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE8);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE9);
        sqLiteDatabase.execSQL(QUE_INSERT_THEME_RECIPE10);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY1);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY2);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE1);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE2);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE3);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE4);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE5);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE6);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE7);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE8);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE9);
        sqLiteDatabase.execSQL(QUE_INSERT_CATEGORY_RECIPE10);
    }
    public SQLiteDatabase getReadableDatabase() {
        throw new RuntimeException("Stub!");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_NOTE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_USER);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_WISHLIST);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_RECIPE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME_RECIPE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY_RECIPE);
//        onCreate(sqLiteDatabase);
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {

    private String url;
    private String html;
    private Elements[] tables;
    static Element round;
    private ArrayList<HashMap<String, String>> team1;
    private ArrayList<HashMap<String, String>> team2;
    private final int timeoutSecs = 25;
    public static final String JAVABRIDGE_PORT = "80,443";
    //static final php.java.bridge.JavaBridgeRunner runner =php.java.bridge.JavaBridgeRunner.getInstance(JAVABRIDGE_PORT);    


    public static void main(String[] args)
            throws Exception {
//runner.waitFor();
        int pts, o, d, t, as, st, to, fv, ag, cm, rv, pir, Round;
        String winner;

        //create the connection

        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String connectionURL = "jdbc:mysql://localhost:3306/diplwmatiki?";
        conn = DriverManager.getConnection(connectionURL, "root", "");

        // create the statment for the query

        Statement stmt_overal = conn.createStatement();//gia ton pinaka overal
        Statement stmt_result = conn.createStatement();//gia ton pinaka results

        Crawler cra = new Crawler("http://www.euroleague.net/main/results/showgame?gamecode=107&seasoncode=E2016");

        //find the apropriate round

        String a = round.toString();
        String[] s1 = a.split("span");
        String[] s2 = s1[1].split("Round");
        String[] s3 = s2[1].split("<");
        String fix_s3 = s3[0].substring(1);
        int rounds = Integer.parseInt(fix_s3);
        System.out.println(rounds);

        ArrayList<HashMap<String, String>> team1 = cra.getTeam1Data();
        System.out.println(team1.get(0));//i omada
        HashMap<String, String> team11 = team1.get(15);
        System.out.print(
                "#=" + team11.get("#") + "\""
                + ", Player=\"" + team11.get("Player") + "\""
                + ", Min=\"" + team11.get("Min") + "\""
                + ", Pts=\"" + team11.get("Pts") + "\""
                + ", 2FG=\"" + team11.get("2FG") + "\""
                + ", 3FG=\"" + team11.get("3FG") + "\""
                + ", FT=\"" + team11.get("FT") + "\""
                + ", O=\"" + team11.get("O") + "\""
                + ", D=\"" + team11.get("D") + "\""
                + ", T=\"" + team11.get("T") + "\""
                + ", As=\"" + team11.get("As") + "\""
                + ", St=\"" + team11.get("St") + "\""
                + ", To=\"" + team11.get("To") + "\""
                + ", Fv=\"" + team11.get("Fv") + "\""
                + ", Ag=\"" + team11.get("Ag") + "\""
                + ", Cm=\"" + team11.get("Cm") + "\""
                + ", Rv=\"" + team11.get("Rv") + "\""
                + ", PIR=\"" + team11.get("PIR") + "\"");
        System.out.println("");

        System.out.println("\nTEAM2\n");
        ArrayList<HashMap<String, String>> team2 = cra.getTeam2Data();

        System.out.println(team2.get(0));//i omada
        HashMap<String, String> team22 = team2.get(16);
        System.out.print(
                "#=" + team22.get("#") + "\""
                + ", Player=\"" + team22.get("Player") + "\""
                + ", Min=\"" + team22.get("Min") + "\""
                + ", Pts=\"" + team22.get("Pts") + "\""
                + ", 2FG=\"" + team22.get("2FG") + "\""
                + ", 3FG=\"" + team22.get("3FG") + "\""
                + ", FT=\"" + team22.get("FT") + "\""
                + ", O=\"" + team22.get("O") + "\""
                + ", D=\"" + team22.get("D") + "\""
                + ", T=\"" + team22.get("T") + "\""
                + ", As=\"" + team22.get("As") + "\""
                + ", St=\"" + team22.get("St") + "\""
                + ", To=\"" + team22.get("To") + "\""
                + ", Fv=\"" + team22.get("Fv") + "\""
                + ", Ag=\"" + team22.get("Ag") + "\""
                + ", Cm=\"" + team22.get("Cm") + "\""
                + ", Rv=\"" + team22.get("Rv") + "\""
                + ", PIR=\"" + team22.get("PIR") + "\"");
        System.out.println("");

        //save the elements of the first team

        HashMap<String, String> team1_vasi = team1.get(15);
        String team1_v = team1.get(0).get("team");
        String min = team1_vasi.get("Min");
        pts = Integer.parseInt(team1_vasi.get("Pts"));
        String fg2 = team1_vasi.get("2FG");
        String fg3 = team1_vasi.get("3FG");
        String ft = team1_vasi.get("FT");
        o = Integer.parseInt(team1_vasi.get("O"));
        d = Integer.parseInt(team1_vasi.get("D"));
        t = Integer.parseInt(team1_vasi.get("T"));
        as = Integer.parseInt(team1_vasi.get("As"));
        st = Integer.parseInt(team1_vasi.get("St"));
        to = Integer.parseInt(team1_vasi.get("To"));
        fv = Integer.parseInt(team1_vasi.get("Fv"));
        ag = Integer.parseInt(team1_vasi.get("Ag"));
        cm = Integer.parseInt(team1_vasi.get("Cm"));
        rv = Integer.parseInt(team1_vasi.get("Rv"));
        pir = Integer.parseInt(team1_vasi.get("PIR"));
        Round = rounds;

        //save in the database the element of the first team

        String ad1 = "INSERT INTO paroveral (`Team`,`Min`,`Pts`,`2FG`,`3FG`,`FT`,`O`,`D`,`T`,`As`,`St`,`To`,`Fv`,`Ag`,`Cm`,`Rv`,`PIR`,`Round`) "
                + "VALUES ('" + team1_v + "','" + min + "','" + pts + "','" + fg2 + "','" + fg3 + "','" + ft + "','" + o + "','" + d + "','" + t + "','" + as + "','" + st + "','" + to + "','" + fv + "','" + ag + "','" + cm + "','" + rv + "','" + pir + "','" + Round + "');";

        stmt_overal.executeUpdate(ad1);

        //save the elements of the second team

        HashMap<String, String> team2_vasi = team2.get(16);
        String team2_v = team2.get(0).get("team");
        min = team2_vasi.get("Min");
        pts = Integer.parseInt(team2_vasi.get("Pts"));
        fg2 = team2_vasi.get("2FG");
        fg3 = team2_vasi.get("3FG");
        ft = team2_vasi.get("FT");
        o = Integer.parseInt(team2_vasi.get("O"));
        d = Integer.parseInt(team2_vasi.get("D"));
        t = Integer.parseInt(team2_vasi.get("T"));
        as = Integer.parseInt(team2_vasi.get("As"));
        st = Integer.parseInt(team2_vasi.get("St"));
        to = Integer.parseInt(team2_vasi.get("To"));
        fv = Integer.parseInt(team2_vasi.get("Fv"));
        ag = Integer.parseInt(team2_vasi.get("Ag"));
        cm = Integer.parseInt(team2_vasi.get("Cm"));
        rv = Integer.parseInt(team2_vasi.get("Rv"));
        pir = Integer.parseInt(team2_vasi.get("PIR"));
        Round = rounds;

        //save in the database the elements of the second team

        String ad2 = "INSERT INTO paroveral (`Team`,`Min`,`Pts`,`2FG`,`3FG`,`FT`,`O`,`D`,`T`,`As`,`St`,`To`,`Fv`,`Ag`,`Cm`,`Rv`,`PIR`,`Round`) "
                + "VALUES ('" + team2_v + "','" + min + "','" + pts + "','" + fg2 + "','" + fg3 + "','" + ft + "','" + o + "','" + d + "','" + t + "','" + as + "','" + st + "','" + to + "','" + fv + "','" + ag + "','" + cm + "','" + rv + "','" + pir + "','" + Round + "');";

        stmt_overal.executeUpdate(ad2);

        //creat the second team

        if (Integer.parseInt(team1_vasi.get("Pts")) > Integer.parseInt(team2_vasi.get("Pts"))) {
            winner = team1.get(0).get("team");
        } else {
            winner = team2.get(0).get("team");
        }

        //save in the database

        String ad3 = "INSERT INTO paresults (`Team1`,`Team2`,`Score1`,`Score2`,`Winner`,`Round`) "
                + "VALUES ('" + team1_v + "','" + team2_v + "','" + Integer.parseInt(team1_vasi.get("Pts")) + "','" + Integer.parseInt(team2_vasi.get("Pts")) + "','" + winner + "','" + Round + "');";

        stmt_result.executeUpdate(ad3);

        stmt_overal.close();
        stmt_result.close();
        conn.close();

    }

    public Crawler(String url) {
        this.url = url;
        try {
            this.tables = getUrlData();
            team1 = extractTableData(tables[0]);
            team2 = extractTableData(tables[1]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Elements[] getUrlData() throws Exception {
        try {
            this.html = getURLcontent(url);

            Elements round_info = getElementsByClassName("gc-title");
            round = round_info.get(3);

            Elements LocalClubElems = getElementsByClassName("LocalClubStatsContainer");
            Elements RoadClubElems = getElementsByClassName("RoadClubStatsContainer");
            return new Elements[]{LocalClubElems, RoadClubElems};
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private ArrayList<HashMap<String, String>> extractTableData(Elements tableEls) throws Exception {
        Document table = Jsoup.parse(tableEls.html());
        Elements rows = table.getElementsByTag("tr");
        //Elements span = table.getElementsByTag("span");

        ArrayList<HashMap<String, String>> rowMap = new ArrayList();

        Elements span = table.getElementsByTag("span");
        //Elements rows = table.getElementsByTag("tr");

        //ArrayList<HashMap<String, String>> rowMap = new ArrayList();
        HashMap team = new HashMap();
        team.put("team", span.first().text());
        rowMap.add(team);
        for (int i = 0; i < rows.size() / 2; i++) {
            Elements cols = rows.get(i).getElementsByTag("td");
            HashMap<String, String> colMap = new HashMap();
            for (int j = 0; j < cols.size(); j++) {
                String title = null;
                switch (j) {
                    case 0:
                        title = "#";
                        break;
                    case 1:
                        title = "Player";
                        break;
                    case 2:
                        title = "Min";
                        break;
                    case 3:
                        title = "Pts";
                        break;
                    case 4:
                        title = "2FG";
                        break;
                    case 5:
                        title = "3FG";
                        break;
                    case 6:
                        title = "FT";
                        break;
                    case 7:
                        title = "O";
                        break;
                    case 8:
                        title = "D";
                        break;
                    case 9:
                        title = "T";
                        break;
                    case 10:
                        title = "As";
                        break;
                    case 11:
                        title = "St";
                        break;
                    case 12:
                        title = "To";
                        break;
                    case 13:
                        title = "Fv";
                        break;
                    case 14:
                        title = "Ag";
                        break;
                    case 15:
                        title = "Cm";
                        break;
                    case 16:
                        title = "Rv";
                        break;
                    case 17:
                        title = "PIR";
                        break;
                }
                if (title != null) {
                    colMap.put(title, cols.get(j).text());
                }
            }
            rowMap.add(colMap);
        }
        //rowMap.remove(0);
        //rowMap.remove(0);
        //rowMap.remove(14);
        return rowMap;
    }

    private String getURLcontent(String url) throws Exception {
        URL durl;
        BufferedReader reader = null;
        StringBuilder content;

        try {
            durl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) durl.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0");
            connection.setRequestMethod("GET");
            connection.setReadTimeout(timeoutSecs * 1000);

            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private Elements getElementsByClassName(String classname) {
        Document doc = Jsoup.parse(html);
        Elements elem = doc.getElementsByClass(classname);
        return elem.first().getAllElements();
    }

    public ArrayList<HashMap<String, String>> getTeam1Data() {
        return team1;
    }

    public ArrayList<HashMap<String, String>> getTeam2Data() {
        return team2;
    }
}

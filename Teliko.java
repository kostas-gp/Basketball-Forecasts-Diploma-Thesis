
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Teliko {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, SQLException {
        try {
            int home1_winner;
            int home1_lost;
            int against1_lost;
            int against1_winner;
            int home2_winner;
            int home2_lost;
            int against2_lost;
            int against2_winner;
            int round, s1, s2;
            int pts, o, d, t, as, st, to, fv, ag, cm, rv, pir;
            float fg2, fg3, ft;
            String team1_arxiko;
            String team2_arxiko;
            String winner;
            String team1_girou;
            String team2_girou;
            String team;
            float numerator_fg2, denominator_fg2;
            float numerator_fg3, denominator_fg3;
            float numerator_ft, denominator_ft;

            //ftiaxnw tin sindesi
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionURL = "jdbc:mysql://localhost:3306/diplwmatiki?";
            conn = DriverManager.getConnection(connectionURL, "root", "");

            // kathe statement gia kathe query
            Statement stmt_results = conn.createStatement();//gia ton pinaka results
            Statement stmt_overal = conn.createStatement();//gia ton pinaka overal
            Statement stmt_create_table = conn.createStatement();//gia tin dimiourgia pinaka

            //gia ton pinaka results
            String query_results = ("SELECT * FROM  results");
            ResultSet rs_results = stmt_results.executeQuery(query_results);
            ResultSetMetaData rsmd1 = rs_results.getMetaData();

            //gia ton pinaka overal
            String query_overal = ("SELECT * FROM  overal");
            ResultSet rs_overal = stmt_overal.executeQuery(query_overal);
            ResultSetMetaData rsmd2 = rs_overal.getMetaData();

            ArrayList<HashMap<String, String>> data_overal = new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> data_results = new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> data_formas = new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> data_statistika = new ArrayList<HashMap<String, String>>();
            //pernaw ta stoixeia stis listes
            //gia ton results
            ArrayList<String> columns_results = new ArrayList<String>(rsmd1.getColumnCount());

            for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
                columns_results.add(rsmd1.getColumnName(i));
            }
            while (rs_results.next()) {
                HashMap<String, String> row_results = new HashMap<String, String>(columns_results.size());
                for (String col : columns_results) {
                    row_results.put(col, rs_results.getString(col));
                }
                data_results.add(row_results);
            }
            //gia ton overal           
            ArrayList<String> columns_overal = new ArrayList<String>(rsmd2.getColumnCount());

            for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
                columns_overal.add(rsmd2.getColumnName(i));
            }
            while (rs_overal.next()) {
                HashMap<String, String> row_overal = new HashMap<String, String>(columns_results.size());
                for (String col : columns_overal) {
                    row_overal.put(col, rs_overal.getString(col));
                }
                data_overal.add(row_overal);
            }
            int a = 0;
            //mou ftiaxnei tin lista me tin forma
            for (int i = 0; i < data_results.size(); i++) {

                home1_winner = 0;
                home1_lost = 0;
                against1_lost = 0;
                against1_winner = 0;
                home2_winner = 0;
                home2_lost = 0;
                against2_lost = 0;
                against2_winner = 0;
                s1 = 0;
                s2 = 0;
                if (Integer.parseInt(data_results.get(i).get("Round")) > 5) //elegxei an o giros einai megaliteros tou 5
                {
                    team1_arxiko = data_results.get(i).get("Team1");
                    team2_arxiko = data_results.get(i).get("Team2");
                    winner = data_results.get(i).get("Winner");
                    round = Integer.parseInt(data_results.get(i).get("Round"));

                    for (int j = 5; j >= 1; j--) {
                        for (int k = i; k > 0; k--) {
                            //apothikeuw tis times tou girou analoga me to an einai team1 i team2 gia na xerw pou vriskomai gipedouxos i oxi
                            team1_girou = data_results.get(k).get("Team1");
                            team2_girou = data_results.get(k).get("Team2");

                            //psaxnw tin prwti omada (Team1)
                            if (((team1_arxiko.equals(data_results.get(k).get("Team1"))) || (team1_arxiko.equals(data_results.get(k).get("Team2")))) && (Integer.parseInt(data_results.get(k).get("Round")) == (round - j))) {

                                s1++;//ena metriti na xerw oti perase
                                if (team1_arxiko.equals(team1_girou)) {

                                    if (team1_arxiko.equals(data_results.get(k).get("Winner"))) {
                                        home1_winner++;
                                    } else {
                                        home1_lost++;
                                    }
                                } else {
                                    if (team2_girou.equals(data_results.get(k).get("Winner"))) {
                                        against1_winner++;
                                    } else {
                                        against1_lost++;
                                    }
                                }
                            }
                            //psaxnw gia tin 2i omada (team2)
                            if (((team2_arxiko.equals(data_results.get(k).get("Team1"))) || (team2_arxiko.equals(data_results.get(k).get("Team2")))) && (Integer.parseInt(data_results.get(k).get("Round")) == (round - j))) {
                                s2++;
                                if (team2_arxiko.equals(data_results.get(k).get("Team1"))) {
                                    if (team2_arxiko.equals(data_results.get(k).get("Winner"))) {
                                        home2_winner++;
                                    } else {
                                        home2_lost++;
                                    }
                                } else {
                                    if (team1_girou.equals(data_results.get(k).get("Winner"))) {
                                        against2_winner++;
                                    } else {
                                        against2_lost++;
                                    }
                                }
                            }
                            //afou exei perasei vgainei
                            if (s1 == 5 && s2 == 5) {
                                HashMap<String, String> row_formas = new HashMap<String, String>(12);

                                row_formas.put("home1_lost", Integer.toString(home1_lost));
                                row_formas.put("home1_winner", Integer.toString(home1_winner));
                                row_formas.put("against1_lost", Integer.toString(against1_lost));
                                row_formas.put("against1_winner", Integer.toString(against1_winner));
                                row_formas.put("home2_lost", Integer.toString(home2_lost));
                                row_formas.put("home2_winner", Integer.toString(home2_winner));
                                row_formas.put("against2_lost", Integer.toString(against2_lost));
                                row_formas.put("against2_winner", Integer.toString(against2_winner));
                                row_formas.put("Team1", team1_arxiko);
                                row_formas.put("Team2", team2_arxiko);
                                row_formas.put("Winner", winner);
                                row_formas.put("Round", Integer.toString(round));
                                data_formas.add(row_formas);
                                break;
                            }
                        }
                    }
                }
            }
            //ftiaxnw ta statistika
            for (int i = 0; i < data_overal.size(); i++) {
                pts = 0;
                ft = 0;
                o = 0;
                d = 0;
                t = 0;
                as = 0;
                st = 0;
                to = 0;
                fv = 0;
                ag = 0;
                cm = 0;
                rv = 0;
                pir = 0;
                fg2 = 0;
                fg3 = 0;
                numerator_fg2 = 0;
                denominator_fg2 = 0;
                numerator_fg3 = 0;
                denominator_fg3 = 0;
                numerator_ft = 0;
                denominator_ft = 0;

                if (Integer.parseInt(data_overal.get(i).get("Round")) > 5) {
                    round = Integer.parseInt(data_overal.get(i).get("Round"));
                    team = data_overal.get(i).get("Team");

                    for (int j = 5; j >= 1; j--) {
                        for (int k = 0; k < data_overal.size(); k++) {
                            if ((team.equals(data_overal.get(k).get("Team"))) && (Integer.parseInt(data_overal.get(k).get("Round")) == (round - j))) {
                                pts = pts + Integer.parseInt(data_overal.get(k).get("Pts"));

                                String[] parts_fg2 = data_overal.get(k).get("2FG").split("/");
                                numerator_fg2 = numerator_fg2 + Integer.parseInt(parts_fg2[0]);
                                denominator_fg2 = denominator_fg2 + Integer.parseInt(parts_fg2[1]);

                                String[] parts_fg3 = data_overal.get(k).get("3FG").split("/");
                                numerator_fg3 = numerator_fg3 + Integer.parseInt(parts_fg3[0]);
                                denominator_fg3 = denominator_fg3 + Integer.parseInt(parts_fg3[1]);

                                String[] parts_ft = data_overal.get(k).get("FT").split("/");
                                numerator_ft = numerator_ft + Integer.parseInt(parts_ft[0]);
                                denominator_ft = denominator_ft + Integer.parseInt(parts_ft[1]);

                                o = o + Integer.parseInt(data_overal.get(k).get("O"));
                                d = d + Integer.parseInt(data_overal.get(k).get("D"));
                                t = t + Integer.parseInt(data_overal.get(k).get("T"));
                                as = as + Integer.parseInt(data_overal.get(k).get("As"));
                                st = st + Integer.parseInt(data_overal.get(k).get("St"));
                                to = to + Integer.parseInt(data_overal.get(k).get("To"));
                                fv = fv + Integer.parseInt(data_overal.get(k).get("Fv"));
                                ag = ag + Integer.parseInt(data_overal.get(k).get("Ag"));
                                cm = cm + Integer.parseInt(data_overal.get(k).get("Cm"));
                                rv = rv + Integer.parseInt(data_overal.get(k).get("Rv"));
                                pir = pir + Integer.parseInt(data_overal.get(k).get("PIR"));
                                break;
                            }
                        }
                    }
                    HashMap<String, String> row_statistika = new HashMap<String, String>(17);
                    pts = pts / 5;
                    row_statistika.put("Pts", Integer.toString(pts));
                    numerator_fg2 = numerator_fg2 / 5;
                    denominator_fg2 = denominator_fg2 / 5;
                    fg2 = (numerator_fg2 / denominator_fg2) * 100;
                    row_statistika.put("2FG", Float.toString(Math.round(fg2)));
                    numerator_fg3 = numerator_fg2 / 5;
                    denominator_fg3 = denominator_fg2 / 5;
                    fg3 = (numerator_fg3 / denominator_fg2) * 100;
                    row_statistika.put("3FG", Float.toString(Math.round(fg3)));
                    numerator_ft = numerator_ft / 5;
                    denominator_ft = denominator_ft / 5;
                    ft = (numerator_ft / denominator_ft) * 100;
                    row_statistika.put("FT", Float.toString(Math.round(ft)));
                    o = o / 5;
                    row_statistika.put("O", Integer.toString(o));
                    d = d / 5;
                    row_statistika.put("D", Integer.toString(d));
                    t = t / 5;
                    row_statistika.put("T", Integer.toString(t));
                    as = as / 5;
                    row_statistika.put("As", Integer.toString(as));
                    st = st / 5;
                    row_statistika.put("St", Integer.toString(st));
                    to = to / 5;
                    row_statistika.put("To", Integer.toString(to));
                    fv = fv / 5;
                    row_statistika.put("Fv", Integer.toString(fv));
                    ag = ag / 5;
                    row_statistika.put("Ag", Integer.toString(ag));
                    cm = cm / 5;
                    row_statistika.put("Cm", Integer.toString(cm));
                    rv = rv / 5;
                    row_statistika.put("Rv", Integer.toString(rv));
                    pir = pir / 5;
                    row_statistika.put("PIR", Integer.toString(pir));
                    row_statistika.put("Team", team);
                    row_statistika.put("Round", Integer.toString(round));
                    data_statistika.add(row_statistika);
                }
            }
            String team1, team2 = null;
            HashMap<String, String> data_statistika1 = null;
            HashMap<String, String> data_statistika2 = null;
            HashMap<String, String> forma = null;
            ArrayList<HashMap<String, HashMap<String, String>>> teliki_lista_formas = new ArrayList<HashMap<String, HashMap<String, String>>>();
            //ftiaxnw mia teliki lista formas
            for (int i = 0; i < data_formas.size(); i++) {
                s1 = 0;
                s2 = 0;
                team1 = data_formas.get(i).get("Team1");
                team2 = data_formas.get(i).get("Team2");
                forma = data_formas.get(i);
                for (int j = 0; j < data_statistika.size(); j++) {
                    if (data_statistika.get(j).get("Team").equals(team1)) {
                        s1++;
                        data_statistika1 = data_statistika.get(j);
                    }
                    if (data_statistika.get(j).get("Team").equals(team2)) {
                        s2++;
                        data_statistika2 = data_statistika.get(j);
                    }
                    if (s1 == 1 && s2 == 1) {
                        HashMap<String, HashMap<String, String>> row_stat = new HashMap<String, HashMap<String, String>>(3);
                        row_stat.put("Team1_statistika", data_statistika1);
                        row_stat.put("Team2_statistika", data_statistika2);
                        row_stat.put("Forma", forma);
                        teliki_lista_formas.add(row_stat);
                        break;
                    }
                }
            }
            int pts1, o1, d1, t1, as1, st1, to1, fv1, ag1, cm1, rv1, pir1;
            float fg21, fg31, fg22, fg32, ft1, ft2;
            int pts2, o2, d2, t2, as2, st2, to2, fv2, ag2, cm2, rv2, pir2;
            String sql_insert_query, Team1 = null, Team2 = null, Winner = null;
            Statement stmt_query_table = conn.createStatement();

            //apothikeuw stin vasi tin lista
            for (int i = 0; i < teliki_lista_formas.size(); i++) {

                //pernaw ola ta stoixeia stis metavlites
                pts1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("Pts"));
                fg21 = Float.parseFloat(teliki_lista_formas.get(i).get("Team1_statistika").get("2FG"));
                fg31 = Float.parseFloat(teliki_lista_formas.get(i).get("Team1_statistika").get("3FG"));
                ft1 = Float.parseFloat(teliki_lista_formas.get(i).get("Team1_statistika").get("FT"));
                o1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("O"));
                d1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("D"));
                t1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("T"));
                as1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("As"));
                st1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("St"));
                to1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("To"));
                fv1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("Fv"));
                ag1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("Ag"));
                cm1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("Cm"));
                rv1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("Rv"));
                pir1 = Integer.parseInt(teliki_lista_formas.get(i).get("Team1_statistika").get("PIR"));

                pts2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("Pts"));
                fg22 = Float.parseFloat(teliki_lista_formas.get(i).get("Team2_statistika").get("2FG"));
                fg32 = Float.parseFloat(teliki_lista_formas.get(i).get("Team2_statistika").get("3FG"));
                ft2 = Float.parseFloat(teliki_lista_formas.get(i).get("Team2_statistika").get("FT"));
                o2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("O"));
                d2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("D"));
                t2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("T"));
                as2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("As"));
                st2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("St"));
                to2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("To"));
                fv2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("Fv"));
                ag2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("Ag"));
                cm2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("Cm"));
                rv2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("Rv"));
                pir2 = Integer.parseInt(teliki_lista_formas.get(i).get("Team2_statistika").get("PIR"));

                team1 = teliki_lista_formas.get(i).get("Forma").get("Team1");
                team2 = teliki_lista_formas.get(i).get("Forma").get("Team2");
                winner = teliki_lista_formas.get(i).get("Forma").get("Winner");
                round = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("Round"));
                home1_lost = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("home1_lost"));
                home1_winner = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("home1_winner"));
                against1_lost = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("against1_lost"));
                against1_winner = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("against1_winner"));
                home2_lost = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("home2_lost"));
                home2_winner = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("home2_winner"));
                against2_lost = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("against2_lost"));
                against2_winner = Integer.parseInt(teliki_lista_formas.get(i).get("Forma").get("against2_winner"));

                sql_insert_query = "INSERT INTO `forma`(`Team1`, `Team2`, `Winner`, `Round`,`home1_lost`,`home1_winner`,`against1_lost`,`against1_winner`,`home2_lost`,`home2_winner`,`against2_lost`,`against2_winner`,`Pts1`,`2FG1`,`3FG1`,`FT1`,`O1`,`D1`,`T1`,`As1`,`St1`,`To1`,`Fv1`,`Ag1`,`Cm1`,`Rv1`,`PIR1`,`Pts2`,`2FG2`,`3FG2`,`FT2`,`O2`,`D2`,`T2`,`As2`,`St2`,`To2`,`Fv2`,`Ag2`,`Cm2`,`Rv2`,`PIR2`) "
                        + "VALUES (" + team1 + "," + team2 + "," + winner + "," + round + "," + home1_lost + "," + home1_winner + "," + against1_lost + "," + against1_winner + "," + home2_lost + "," + home2_winner + "," + against2_lost + "," + against2_winner + ","
                        + pts1 + "," + fg21 + "," + fg31 + "," + ft1 + "," + o1 + "," + d1 + "," + t1 + "," + as1 + "," + st1 + "," + to1 + "," + fv1 + "," + ag1 + "," + cm1 + "," + rv1 + "," + pir1 + ","
                        + pts2 + "," + fg22 + "," + fg32 + "," + ft2 + "," + o2 + "," + d2 + "," + t2 + "," + as2 + "," + st2 + "," + to2 + "," + fv2 + "," + ag2 + "," + cm2 + "," + rv2 + "," + pir2 + ")";
                String ad = "INSERT INTO forma(Team1, Team2, Winner, Round,home1_lost,home1_winner,against1_lost,against1_winner,home2_lost,home2_winner,against2_lost,against2_winner,Pts1,2FG1,3FG1,FT1,O1,D1,T1,As1,St1,To1,Fv1,Ag1,Cm1,Rv1,PIR1,Pts2,2FG2,3FG2,FT2,O2,D2,T2,As2,St2,To2,Fv2,Ag2,Cm2,Rv2,PIR2) "
                        + "VALUES ('" + team1 + "','" + team2 + "','" + winner + "','" + round + "','" + home1_lost + "','" + home1_winner + "','" + against1_lost + "','" + against1_winner + "','" + home2_lost + "','" + home2_winner + "','" + against2_lost + "','" + against2_winner + "','"
                        + pts1 + "','" + fg21 + "','" + fg31 + "','" + ft1 + "','" + o1 + "','" + d1 + "','" + t1 + "','" + as1 + "','" + st1 + "','" + to1 + "','" + fv1 + "','" + ag1 + "','" + cm1 + "','" + rv1 + "','" + pir1 + "','"
                        + pts2 + "','" + fg22 + "','" + fg32 + "','" + ft2 + "','" + o2 + "','" + d2 + "','" + t2 + "','" + as2 + "','" + st2 + "','" + to2 + "','" + fv2 + "','" + ag2 + "','" + cm2 + "','" + rv2 + "','" + pir2 + "');";
                stmt_query_table.executeUpdate(ad);
            }

            rs_results.close();
            stmt_results.close();
            rs_overal.close();
            stmt_overal.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Teliko.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

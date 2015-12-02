package model;

/**
 * Created by dionys on 29/11/15.
 */
public class JsonOffLine {


    public String login (){

        String login = "{"+
            "           status: true," +
            "           error: null,"+
            "           value: {"+
            "              professor_id: 1,"+
            "              token: 123456789"+
            "           }"+
            "          }";

        return login;
    }


    public String listeEtablissements() {
        String etablissement = "{"+
                        "status: true,"+
                        "error: null,"+
                        "value: {"+
                            "school: ["+
                                 "{"+
                                    "id: 1,"+
                                    "name:"+ "Acadomia"+
                                  "},"+
                                "{"+
                                    "id: 2,"+
                                    "name:"+ "Acadomia2"+
                                "}"+
                            "]"+
                        "}"+
                    "}";

        return etablissement;
    }
}

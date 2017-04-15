package wildtornado.org.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSetParser {

    private static File file;
    private Object[][] data;

    public DataSetParser(String fileLocation) {
        file = new File(fileLocation);

        try {
            parseData();
        } catch (IOException e) {
            Logger logger = Logger.getLogger("logger");
            logger.log(Level.SEVERE, "File not found: ", e);
        }
    }

    public Object[][] getData() {
        return data;
    }

    private void parseData() throws IOException {
        String del = ",";
        String line;
        BufferedReader r = new BufferedReader(new FileReader(file));
        List<String[]> list = new ArrayList<>();
        while ((line = r.readLine()) != null)
            list.add(line.split(del));

        data = new Object[list.size()][];
        list.toArray(data);

        r.close();
    }

    public List<Double> getValueList(int i) {
        List<Double> valueList = new ArrayList<Double>();
        for (Object[] row : data) {
            valueList.add(Util.getVal(row[i]));
        }
        return valueList;
    }


}

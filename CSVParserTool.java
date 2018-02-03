import java.util.Vector;

public class CSVParserTool
{
	public static void main(String args[])
	{
		DataSet ds = new DataSet();
		ds.setDataSetBoundaries(4, 10);
		ds.loadData("C:\\Users\\cdimaio\\Dropbox\\MyProjects\\DataStuff\\CSVParserTool\\testdata.csv");
		ds.printDataSet();
		
		// removeNestedCommas("1-4 page,\"$3,341\",\"$3,675\",\"$4,043\",\"$4,447\",\"$4,892\"");
	}
	
	public static String removeNestedCommas(String dirtyString)
	{
		String cleanString = "";
		
		int endOfString = dirtyString.length();
		
		int firstQuoteIndex = dirtyString.indexOf("\"");
		int startQuoteIndex = -1;
		int endQuoteIndex = -1;
		
		cleanString = dirtyString.substring(0, firstQuoteIndex-1);
		
		String strInsideQuotes = "";
		for(int i = firstQuoteIndex; i < endOfString; i++)
		{
			startQuoteIndex = dirtyString.indexOf("\"", i);
			endQuoteIndex = dirtyString.indexOf("\"", startQuoteIndex+1);
			strInsideQuotes = dirtyString.substring(startQuoteIndex+1, endQuoteIndex);
			if(cleanString.length() == 0)
			{
				cleanString += "\"" + strInsideQuotes.replaceAll(",", "") + "\"";
			}
			else
			{
				cleanString += ",\"" + strInsideQuotes.replaceAll(",", "") + "\"";
			}
			i = endQuoteIndex;
			System.out.println(strInsideQuotes);
		}
		
		return cleanString;
	}
}
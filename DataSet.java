import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.ArrayList;

public class DataSet
{
	Vector dataSet = null;
	
	ArrayList<Integer> ignoreList = null;
	
	int endOfDataSet = -1;
	
	DataSet()
	{
		dataSet = new Vector();
		ignoreList = new ArrayList();
	}
	
	public void setDataSetBoundaries(int start, int end)
	{
		ignoreLines(0, start-1);
		endOfDataSet = end+1;
	}
	
	public void ignoreLine(int lineNumber)
	{
		ignoreList.add(lineNumber);
	}
	
	public void ignoreLines(int start, int end)
	{
		for(int i = start; i < end+1; i++)
		{		
			ignoreLine(i);
		}
	}
	
	public void loadData(String filePath)
	{
		try
		{
			FileInputStream fstream = new FileInputStream(filePath);
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int lineNumber = 1;
			while ((strLine = br.readLine()) != null)
			{
				if(!ignoreList.contains(lineNumber))
				{
					dataSet.add(parseRow(strLine));
				}
			
				lineNumber++;
				
				if(isEndOfDataSet(lineNumber))
				{
					break;
				}
			}
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void printDataSet()
	{
		for(int i = 0; i < dataSet.size(); i++)
		{
			Vector tmp = (Vector)dataSet.get(i);
			for(int j = 0; j < tmp.size(); j++)
			{
				System.out.print(tmp.get(j));
				System.out.print("|");
			}
			System.out.println();
		}
	}
	
	private boolean isEndOfDataSet(int e)
	{
		return (e == endOfDataSet);
	}
	
	private Vector parseRow(String row)
	{
		Vector rowVector = new Vector();
		
		row = removeNestedCommas(row);
		
		String columns[] = row.split(",");
		
		for(int i = 0; i < columns.length; i++)
		{
			// System.out.println(columns[i]);
			rowVector.add(columns[i]);
		}
		return rowVector;
	}
	
	private static String removeNestedCommas(String dirtyString)
	{
		String cleanString = "";
		
		if(dirtyString != null)
		{
			int endOfString = dirtyString.length();
			
			int firstQuoteIndex = dirtyString.indexOf("\"");
			int startQuoteIndex = -1;
			int endQuoteIndex = -1;
			
			if(firstQuoteIndex != -1)
			{
				cleanString = dirtyString.substring(0, firstQuoteIndex-1);
				
				String strInsideQuotes = "";
				for(int i = firstQuoteIndex; i < endOfString; i++)
				{
					startQuoteIndex = dirtyString.indexOf("\"", i);
					endQuoteIndex = dirtyString.indexOf("\"", startQuoteIndex+1);
					
					if(startQuoteIndex != -1)
					{
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
					}
				}
			}
			else
			{
				cleanString = dirtyString;
			}
		}
		
		return cleanString;
	}
}










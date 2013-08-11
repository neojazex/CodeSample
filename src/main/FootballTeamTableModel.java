package main;

import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Model for the table displaying Team Data
 * @author Jason
 *
 */
public class FootballTeamTableModel extends AbstractTableModel
{
    private String[] columnNames = new String[]{};
    private Team[] data = new Team[]{};
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Team value = data[row];
		Object toReturn = null;
		switch(col)
		{
			case 0: toReturn = value.getPos();
				break;
			case 1: toReturn = value.getTeam();
				break;
			case 2: toReturn = value.getTeamP();
				break;
			case 3: toReturn = value.getTeamW();
				break;
			case 4: toReturn = value.getTeamD();
				break;
			case 5: toReturn = value.getTeamL();
				break;
			case 6: toReturn = value.getTeamFor();
				break;
			case 7: toReturn = value.getTeamAgn();
				break;
			case 8: toReturn = value.getMax();
				break;
			case 9: toReturn = value.getMin();
				break;
		}
		return toReturn;
	}

	public void setColumnNames(List<String> headerNames)
	{
		columnNames = (String[]) headerNames.toArray(columnNames);
	}

	public void setData(List<Team> teamList)
	{
		data = (Team[]) teamList.toArray(data);
	}

}

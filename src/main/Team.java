package main;

/**
 * Object to store the data for each football team
 * 
 * @author Jason
 * 
 */
public class Team
{

	/**
	 * Position
	 */
	private int pos;

	/**
	 * Team Name
	 */
	private String team;

	/**
	 * Played
	 */
	private int teamP;

	/**
	 * Win
	 */
	private int teamW;

	/**
	 * Draw
	 */
	private int teamD;

	/**
	 * Loss
	 */
	private int teamL;

	private int teamFor;

	private int teamAgn;

	private int max;

	private int min;

	public int getPos()
	{
		return pos;
	}

	public void setPos(int pos)
	{
		this.pos = pos;
	}

	public String getTeam()
	{
		return team;
	}

	public void setTeam(String team)
	{
		this.team = team;
	}

	public int getTeamP()
	{
		return teamP;
	}

	public void setTeamP(int teamP)
	{
		this.teamP = teamP;
	}

	public int getTeamW()
	{
		return teamW;
	}

	public void setTeamW(int teamW)
	{
		this.teamW = teamW;
	}

	public int getTeamD()
	{
		return teamD;
	}

	public void setTeamD(int teamD)
	{
		this.teamD = teamD;
	}

	public int getTeamL()
	{
		return teamL;
	}

	public void setTeamL(int teamL)
	{
		this.teamL = teamL;
	}

	public int getTeamFor()
	{
		return teamFor;
	}

	public void setTeamFor(int teamFor)
	{
		this.teamFor = teamFor;
	}

	public int getTeamAgn()
	{
		return teamAgn;
	}

	public void setTeamAgn(int teamAgn)
	{
		this.teamAgn = teamAgn;
	}

	public int getMax()
	{
		return max;
	}

	public void setMax(int max)
	{
		this.max = max;
	}

	public int getMin()
	{
		return min;
	}

	public void setMin(int min)
	{
		this.min = min;
	}
}

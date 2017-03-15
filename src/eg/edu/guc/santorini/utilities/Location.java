package  eg.edu.guc.santorini.utilities;

public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Location(Location l)
	{
		this.x = l.getX();
		this.y = l.getY();
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * @return true if location has the same coordinates as this 
	 */
	public boolean equals(Location location)
	{
		return this.getX() == location.getX() && this.getY() == location.getY();
	}
	
	/**
	 * @param location
	 * @return Adds the coordinates of location and this, and returns the new location 
	 */
	public Location addLocation(Location location)
	{
		return new Location(this.getX() + location.getX(), this.getY() + location.getY());
	}
	@Override 
	public String toString()
	{
		return " [ " + y + " " + x + " ] "; 
	}
}

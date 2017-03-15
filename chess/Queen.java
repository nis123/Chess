package chess;

public class Queen extends Piece{
	private String color;
	private Location location;
	boolean check = false;
	public Queen(Location location, String color){
		this.location = location;
		this.color = color;
	}
	@Override
	boolean canMove(Location newLoc, Board b) {
		Piece checkPiece = this.getCheckPiece(b);
		if (checkPiece!=null)
		{
			Location checkLoc = checkPiece.getLocation();
			if (b.check == true &&!(this.canMove(checkLoc, b)) )
			{
				return false;
			}
		}
		int currFile = location.convertX();
		int currRank = location.getY();
		int newFile = newLoc.convertX();
		int newRank = newLoc.getY();
		int filediff = Math.abs(newFile-currFile);
		int rankdiff = Math.abs(newRank-currRank);
		if (filediff == rankdiff){
			System.out.println(1);
			if(b.board[newRank][newFile] == null)
				return true;
			else if(!b.board[newRank][newFile].getColor().equals(this.color))
				return true;
		}
		else if(filediff > 0 && rankdiff == 0){
			System.out.println(2);
			if(b.board[newRank][newFile] == null)
				return true;
			else if(!b.board[newRank][newFile].getColor().equals(this.color))
				return true;
		}
		else if(rankdiff > 0 && filediff == 0){
			System.out.println(3);
			if(b.board[newRank][newFile] == null)
				return true;
			else if(!b.board[newRank][newFile].getColor().equals(this.color))
				return true;
		}
		return false;
	}

	@Override
	boolean moveTo(Location newLoc, Board b) {
		if (this.canMove(newLoc, b))
		{
			this.location = newLoc;
			Location opposKingsLoc = this.getKingLocation(this.color, b);
			//System.out.println("Bishop's loc: " + this.getLocation().getX()+ " " + this.getLocation().getY());
			//System.out.println("King's Loc: " + opposKingsLoc.getX() + " " + opposKingsLoc.getY());
			//System.out.println("called getKingLoc");
			if (canMove(opposKingsLoc,b))
			{
				//System.out.println("entered");
				b.check = true;
				System.out.println("Check");
			}
			return true;
		}
		System.out.println("Illegal move, try again");
		System.out.println();
		return false;
	}

	@Override
	String getColor() {
		return color;
	}
	public String toString()
	{
		if (color.equals("white"))
		{
			return "wQ";
		}
		return "bQ";
	}
	public Location getLocation()
	{
		return location;
	}
	public void setLocation(Location newLoc)
	{
		location = newLoc;
	}
}

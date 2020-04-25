import java.util.ArrayList;

public class Level {
	ArrayList<Component> elementos;
	int ID;
	
	Level(int n){
		elementos = new ArrayList<Component>();
		ID = n;
	}
	
	void add(Component c){
		elementos.add(c);
	}
	
	int getID(){
		return ID;
	}
}
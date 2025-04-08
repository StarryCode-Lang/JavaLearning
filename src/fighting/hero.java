package fighting;
import java.util.Random;
public class hero {
    private final String name;
    private int HP = 500;
    public hero(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getHP() {
        return this.HP;
    }
    public boolean isDead(){
        return this.getHP() <= 0;
    }
    public void attack(hero other) {
        if (other.getHP() > 0) {
            Random random = new Random();
            int min = 10;
            int max = 20;
            int recover = random.nextInt(max - min + 1) + min;
            int damage = random.nextInt(max - min + 1) + min;
            other.reduceHP(damage);
            this.recoverHP(recover);
        }
    }
    public void reduceHP(int value1) {
        this.HP -= value1;
    }
    public void recoverHP(int value2){
        this.HP += value2;
        if(this.HP > 500){
            this.HP = 500;
        }
    }

}

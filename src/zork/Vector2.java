package zork;

import java.util.Objects;

public class Vector2 {
    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x;
    public int y;

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }

        if (!(o instanceof Vector2)){
            return false;
        }

        Vector2 vector2 = (Vector2) o;

        return (this.x == vector2.x && this.y == vector2.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

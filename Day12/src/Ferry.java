public class Ferry {

    private int xPos;
    private int yPos;
    private int facing;
    private int xWay;
    private int yWay;

    public Ferry() {
        this.xPos = 0;
        this.yPos = 0;
        this.facing = 0;
    }

    public int getX() {
        return this.xPos;
    }

    public int getY() {
        return this.yPos;
    }

    public String printWay() {
        return"(" + this.xWay + ", " + this.yWay+")";
    }

    public void setWayPoint(int x, int y) {
        this.xWay = x;
        this.yWay = y;
    }

    private void rotateWay(int i) {
        i *= -1;
        while (i < 0) {
            i += 360;
        }
        for (int j = 0; j < i; j += 90) {
            int temp;
            if (this.xWay * this.yWay < 0) {
                temp = this.xWay;
                this.xWay = yWay;
                this.yWay = -1 * temp;
            } else if (this.xWay * this.yWay > 0) {
                temp = this.yWay;
                this.yWay = -1 * xWay;
                this.xWay = temp;
            } else if (this.xWay == 0) {
                this.xWay = this.yWay;
                this.yWay = 0;
            } else if (this.yWay == 0) {
                this.yWay = -1 * this.xWay;
                this.xWay = 0;
            }
        }
    }

    private void forwardWay(int n) {
        for (int i = 0; i < n; i++) {
            this.xPos = this.xPos + this.xWay;
            this.yPos = this.yPos + this.yWay;
        }
    }

    public void moveWay(char c, int i) {
        if (c == 'R')
            rotateWay(-1 * i);
        else if (c == 'L')
            rotateWay(i);
        else if (c == 'F')
            forwardWay(i);
        else if (c == 'N')
            this.yWay += i;
        else if (c == 'E')
            this.xWay += i;
        else if (c == 'S')
            this.yWay -= i;
        else if (c == 'W')
            this.xWay -= i;
    }

    private void rotate(int degrees) {
        this.facing += degrees;
        while(this.facing < 0) {
            this.facing += 360;
        }
        this.facing = this.facing % 360;
    }

    private void forward(int i) {
        if (this.facing == 0)
            move('E', i);
        else if (this.facing == 270)
            move('S', i);
        else if (this.facing == 180)
            move('W', i);
        else if (this.facing == 90)
            move('N', i);
    }

    public void move(char c, int i) {
        if (c == 'R')
            rotate(-1 * i);
        else if (c == 'L')
            rotate(i);
        else if (c == 'F')
            forward(i);
        else if (c == 'N')
            this.yPos += i;
        else if (c == 'E')
            this.xPos += i;
        else if (c == 'S')
            this.yPos -= i;
        else if (c == 'W')
            this.xPos -= i;
    }
}

package HomeWork5;

public class Animal {
    protected String name, type;
    protected final float limitSwim, limitJump, limitRun;
    protected final float averageLimitRun, averageLimitSwim, averageLimitJump;
    protected int score;

    Animal(String name, float averageLimitSwim, float averageLimitJump, float averageLimitRun) {
        this.name = name;
        this.averageLimitSwim = averageLimitSwim;
        this.averageLimitJump = averageLimitJump;
        this.averageLimitRun = averageLimitRun;
        //разброс в 20% от средней нормы
        this.limitJump = Math.round(averageLimitJump * percent(80, 120)) / 100f;
        this.limitRun = Math.round(averageLimitRun * percent(80, 120)) / 100f;
        this.limitSwim = Math.round(averageLimitSwim * percent(80, 120)) / 100f;
    }

    protected String getLimits() {
        return name + " имеет ограничения: плавание(м) - " + limitSwim + ", прыжок(м) - " + limitJump + ", бег(м) - " + limitRun + ".";
    }

    protected String getAverageLimits() {
        return type + " в среднем имеет ограничения: плавание(м) - " + averageLimitSwim + ", прыжок(м) - " + averageLimitJump + ", бег(м) - " + averageLimitRun + ".";
    }

    protected String jump(float height) {
        String txt;
        if (height <= limitJump) {
            txt = name + " взял высоту " + height + "м и справился с заданием(предел - " + limitJump + "м)!";
            this.score++;
        } else txt = name + " подпрыгнул всего на " + limitJump + "м и не смог взять высоту " + height + ".";
        return txt;
    }

    protected String run(float length) {
        String txt;
        if (length <= limitRun) {
            txt = name + " смог пробежать " + length + "м и справился с заданием(предел - " + limitRun + "м)!";
            this.score++;
        } else
            txt = name + " пробежал всего " + limitRun + "м и не смог бежать дальше. Это его личный предел для бега.";
        return txt;
    }

    protected String swim(float length) {
        String txt;
        if (length <= limitSwim) {
            txt = name + " смог проплыть " + length + "м и справился с заданием(предел - " + limitSwim + "м)!";
            this.score++;
        } else
            txt = name + " проплыл всего " + limitSwim + "м и не смог больше плыть. Спасатели из прошлой домашки успели вовремя!";
        return txt;
    }

    private float percent(int min, int max) {
        return (Math.round(Math.random() * (max - min) * 100.0) / 100f + min);
    }
}
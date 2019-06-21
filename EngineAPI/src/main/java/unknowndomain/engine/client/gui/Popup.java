package unknowndomain.engine.client.gui;

import com.github.mouse0w0.lib4j.observable.value.*;
import unknowndomain.engine.client.gui.misc.Pos;

public class Popup {
    private final MutableIntValue width = new SimpleMutableIntValue();
    private final MutableIntValue height = new SimpleMutableIntValue();
    private final MutableFloatValue x = new SimpleMutableFloatValue();
    private final MutableFloatValue y = new SimpleMutableFloatValue();
    private Container content;
    private Component parent;
    private Snap snap;

    public Popup(Container content){
        this.content = content;
    }

    public Snap getSnap() {
        return snap;
    }

    public void setSnap(Snap snap) {
        this.snap = snap;
    }


    public void setSize(int width, int height) {
        this.width.set(width);
        this.height.set(height);
        updateRoot();
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public Component getParent() {
        return parent;
    }

    public int getWidth() {
        return width.get();
    }

    public int getHeight() {
        return height.get();
    }

    public ObservableFloatValue getX() {
        return x.toImmutable();
    }

    public ObservableFloatValue getY() {
        return y.toImmutable();
    }

    private void updateRoot() {
        this.content.width.set(getWidth() - content.x().get());
        this.content.height.set(getHeight() - content.y().get());
        this.content.needsLayout();
    }

    public void update() {
        content.layout();
    }

    public enum Snap{
        CURSOR(Pos.CENTER),
        COMPONENT_DOWN(Pos.BOTTOM_LEFT),
        COMPONENT_UP(Pos.TOP_LEFT);

        private final Pos anchor;

        Snap(Pos anchor){
            this.anchor = anchor;
        }
    }
}

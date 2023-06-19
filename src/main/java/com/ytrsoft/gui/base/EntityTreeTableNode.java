package com.ytrsoft.gui.base;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

public class EntityTreeTableNode<T> extends DefaultMutableTreeTableNode {

    public EntityTreeTableNode() {}

    public EntityTreeTableNode(T t) {
        super(t);
    }

}
package org.stats.ui.elements;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

import org.springframework.util.CollectionUtils;
import org.stats.core.interfaces.IdNameInterface;

public class IdNameComboBox extends JComboBox<String>{
	private static final long serialVersionUID = 6797028408959934317L;
	
	private Map<String, IdNameInterface> elementsMap = new HashMap<String, IdNameInterface>();
	private Map<Long, String> idMap = new HashMap<Long, String>();

	public IdNameComboBox(Collection<? extends IdNameInterface> itemList) {
		super();
		if(!CollectionUtils.isEmpty(itemList)) {
			itemList.stream().forEach(this::addNewItem);
		}
	}
	
	private void addNewItem(IdNameInterface item) {
		elementsMap.put(item.getName(), item);
		idMap.put(item.getId(), item.getName());
		addItem(item.getName());
	}

	public Long getSelectedId() {
		return elementsMap.get(getSelectedItem()).getId();
	}
	
	public void setSelectedId(Long selectedId) {
		if(null != selectedId) {
			setSelectedItem(idMap.get(selectedId));
		}
	}
}

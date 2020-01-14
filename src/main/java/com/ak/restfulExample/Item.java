package com.ak.restfulExample;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Item {

	private String id, name, description, brand, category;
	private String createdAt;
	private List<String> tags;
	private long createdAtTime;

	public Item() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	long getCreatedAtTime() {
		return createdAtTime;
	}
	void setCreatedAtTime(long createdAtTime) {
		this.createdAtTime = createdAtTime;
		createdAt = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(createdAtTime));
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	@Override
	public int hashCode() {
		return (id == null) ? 0 : id.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", brand=" + brand + ", category="
				+ category + ", createdAt=" + createdAt + ", tags=" + tags + "]";
	}

}



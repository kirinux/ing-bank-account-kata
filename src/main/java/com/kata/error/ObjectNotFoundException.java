package com.kata.error;

public class ObjectNotFoundException extends RuntimeException {
	public enum ObjectType{
		ACCOUNT("Account"),
		CUSTOMER("Customer");	
		private final String name;
		ObjectType(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	private ObjectType objectType;
	private long id;
	public ObjectNotFoundException(long id, ObjectType objectType) {
	       super(objectType.toString() + " id : "+ id + " not found.");
	       this.objectType = objectType;
	       this.id = id;
	}
	public ObjectType getObjectType() {
		return objectType;
	}
	
	public long getId() {
		return id;
	}

}

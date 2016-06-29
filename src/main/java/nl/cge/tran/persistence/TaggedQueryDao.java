package nl.cge.tran.persistence;

import nl.cge.tran.domein.TaggedQuery;

public class TaggedQueryDao extends EntityDao<TaggedQuery> {

	public static TaggedQueryDao instance() {
		return new TaggedQueryDao();
	}

	@Override
	protected String getDatabaseFilename() {
		return "taggedQuery";
	}

	public void delete(TaggedQuery taggedQuery) {
		getMap().remove(taggedQuery.getId());
	}

}

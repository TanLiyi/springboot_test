package com.liyi.utils;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class OffsetRequest implements Pageable {
	private final int offset;
	private final int size;
	private final Sort sort;

	public OffsetRequest(int offset, int size, Sort sort) {
		this.offset = offset;
		this.size = size;
		this.sort = sort;
	}

	public OffsetRequest(int offset, int size) {
		this(offset, size, null);
	}

	@Override
	public int getPageNumber() {
		throw new NotImplementedException("getPageNumber");
	}

	@Override
	public int getPageSize() {
		return size;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public Pageable next() {
		return new OffsetRequest(offset + size, size, sort);
	}

	@Override
	public Pageable previousOrFirst() {
		int p = offset - size;
		p = p > 0 ? p : 0;
		return new OffsetRequest(p, size, sort);
	}

	@Override
	public Pageable first() {
		return new OffsetRequest(0, size, sort);
	}

	@Override
	public boolean hasPrevious() {
		return offset > 0;
	}
}

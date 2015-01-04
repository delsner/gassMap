package gassMap.springSecurity

import org.apache.commons.lang.builder.HashCodeBuilder

class PersonRole implements Serializable {

	private static final long serialVersionUID = 1

	Person user
	Role role

	boolean equals(other) {
		if (!(other instanceof PersonRole)) {
			return false
		}

		other.user?.id == user?.id &&
		other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static PersonRole get(long userId, long roleId) {
		PersonRole.where {
			user == Person.load(userId) &&
			role == Role.load(roleId)
		}.get()
	}

	static boolean exists(long userId, long roleId) {
		PersonRole.where {
			user == Person.load(userId) &&
			role == Role.load(roleId)
		}.count() > 0
	}

	static PersonRole create(Person user, Role role, boolean flush = false) {
		def instance = new PersonRole(user: user, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Person u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = PersonRole.where {
			user == Person.load(u.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { PersonRole.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Person u, boolean flush = false) {
		if (u == null) return

		PersonRole.where {
			user == Person.load(u.id)
		}.deleteAll()

		if (flush) { PersonRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		PersonRole.where {
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { PersonRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, PersonRole ur ->
			if (ur.user == null) return
			boolean existing = false
			PersonRole.withNewSession {
				existing = PersonRole.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}

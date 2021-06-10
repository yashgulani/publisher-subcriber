package pub_sub.constants;

// Defines behaviour for regex filter.
public enum FilterCondition {
	// Allow message if regex matches.
	ALLOW_IF_MATCH,

	// Deny message if regex matches.
	DENY_IF_MATCH
}

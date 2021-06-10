package pub_sub.messageFilter;

import java.util.Objects;
import java.util.regex.Pattern;

import pub_sub.constants.FilterCondition;
import pub_sub.dto.FilterRequest;
import pub_sub.exceptions.InvalidRequestException;
import pub_sub.exceptions.MessageFilterException;

public class RegexFilter implements MessageFilter {

	private Pattern pattern;
	private String regex;
	private final FilterCondition filterCondition;

	public RegexFilter(String regex, FilterCondition filterCondition)
		throws MessageFilterException {
		this.filterCondition = filterCondition;
		this.regex = regex;
		compileRegex(regex);
	}

	public RegexFilter(Pattern pattern, FilterCondition filterCondition) {
		this.pattern = pattern;
		this.filterCondition = filterCondition;
	}

	private void compileRegex(String regex) throws MessageFilterException {
		try {
			this.pattern = Pattern.compile(regex);
		} catch (Exception e) {
			throw new MessageFilterException("Error while handling regex", e);
		}
	}
	public String getRegex() {
    return this.regex;
  }

	@Override
	public boolean shouldFilterMessage(FilterRequest filterRequest) throws MessageFilterException {
		validate(filterRequest);
		boolean matchResult = pattern.matcher(filterRequest.getMessage().getEventName()).matches();
		return buildResult(matchResult);
	}

	private boolean buildResult(boolean matchResult) {
		if (filterCondition.equals(FilterCondition.ALLOW_IF_MATCH)) {
			return !matchResult;
		}
		return matchResult;
	}

	private void validate(FilterRequest filterRequest) throws InvalidRequestException {
		if (Objects.isNull(filterRequest.getMessage())) {
			throw new InvalidRequestException("body cannot be null");
		}
	}
}

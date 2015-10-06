<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tbody>
			<tr>
				<td>
					<table>
						<tbody>
							<tr>
								<td colspan="3"><span style="font-weight: bold;">MaxStop</span>
									<input type="radio" name="stopType" value="global"
									checked="checked" /> <em>Global</em> <input type="radio"
									name="stopType" value="individual" /> <em>Individual ...</em></td>
							</tr>
							<tr id="globalMaxStop">
								<th>MaxStop Global</th>
								<td><input list="maxNums" name="maxStop" /> <datalist
										id="maxNums">
										<option value="0">
										<option value="1">
										<option value="2">
										<option value="3">
										<option value="ANY">
									</datalist></td>
								<td><img src="images/line_double_arrow_end.png"
									title="Click to add this filter" onClick="push('maxStop')" /></td>
							</tr>
							<tr class="onMaxStop">
								<th>MaxStop On Move</th>
								<td><input list="maxNums" name="maxStopOnMove" /></td>
								<td><img src="images/line_double_arrow_end.png"
									onClick="push('maxStopOnMove')" /></td>
							</tr>
							<tr class="onMaxStop">
								<th>MaxStop On Return</th>
								<td><input list="maxNums" name="maxStopOnReturn" /></td>
								<td><img src="images/line_double_arrow_end.png"
									onClick="push('maxStopOnReturn')" /></td>
							</tr>
							<tr>
								<th>Max Price</th>
								<td><input type="text" id="maxPrice" name="maxPrice" /></td>
								<td><img src="images/line_double_arrow_end.png"
									onClick="push('maxPrice')" /></td>
							</tr>
							<tr>
								<th>Relative Max Price</th>
								<td><input type="text" id="relativeMaxPrice"
									name="relativeMaxPrice" /></td>
								<td><img src="images/line_double_arrow_end.png"
									onClick="push('relativeMaxPrice')" /></td>
							</tr>
						</tbody>
					</table>
				</td>
				<td>
					<p
						style="vertical-align: top; text-align: center; font-weight: bold;">Filter
						List</p> <div id="filterList" ></div>
				</td>
			</tr>
		</tbody>
	</table>
	<script>
		function push(el) {
			if (el == 'relativeMaxPrice') {
				$('#filterList').append(
								'<tr><td>'
										+ relativeMaxPrice
										+ ':'
										+ $('#relativeMaxPrice').value()
										+ '<img src="images/remove-icon-30.png" onclick="$(this).parent().parent().remove()" /></td></tr>');
			} else if (el == 'maxPrice') {
				$('#filterList')
						.append(
								'<tr><td>'
										+ maxPrice
										+ ':'
										+ $('#maxPrice').value()
										+ '<img src="images/remove-icon-30.png" onclick="$(this).parent().parent().remove()" /></td></tr>');
			} else if (el == 'maxStop') {
				$('#filterList')
						.append(
								'<tr><td>'
										+ maxStop
										+ ':'
										+ $('#maxStop').value()
										+ '<img src="images/remove-icon-30.png" onclick="$(this).parent().parent().remove()" /></td></tr>');
			} else if (el == 'maxStopOnMove') {
				$('#filterList')
						.append(
								'<tr><td>'
										+ maxStopOnMove
										+ ':'
										+ $('#maxStopOnMove').value()
										+ '<img src="images/remove-icon-30.png" onclick="$(this).parent().parent().remove()" /></td></tr>');
			} else if (el == 'maxStopOnReturn') {
				$('#filterList')
						.append(
								'<tr><td>'
										+ maxStopOnReturn
										+ ':'
										+ $('#maxStopOnReturn').value()
										+ '<img src="images/remove-icon-30.png" onclick="$(this).parent().parent().remove()" /></td></tr>');
			}
		}

		$(document).ready(function() {
			$('#globalMaxStop').show();
			$('.onMaxStop').hide();
			$('#stopType').change(function() {
				var option = $('#stopType option:selected');
				if (option == 'global') {
					$('#globalMaxStop').show();
					$('.onMaxStop').hide();
				} else {
					$('#globalMaxStop').hide();
					$('.onMaxStop').show();
				}
			});
		});
	</script>
</html>
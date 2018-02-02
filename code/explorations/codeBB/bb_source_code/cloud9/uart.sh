while read -r line < /dev/ttyS1; do
  # $line is the line read, do something with it
  echo $result > /dev/ttyS1
done
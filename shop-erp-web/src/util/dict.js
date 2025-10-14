export const getOptions = () => {
  const dictMap = dict;
  const optionMap = {};
  for (let key in dictMap) {
    const dictArray = [];
    for (let t in dictMap[key]) {
      dictArray.push({
        label: dictMap[key][t],
        value: Number(t),
      });
    }
    optionMap[key] = dictArray;
  }
  return optionMap;
};

export const convertDict = (key, value) => {
  const dictMap = dict[key];
  return dictMap[value] || '';
};

export const dict = {
  SEX: {
    1: '男',
    2: '女',
  },
  STANDARD: {
    1: '只',
    2: '件',
    3: '箱',
    4: '个'
  }
};
